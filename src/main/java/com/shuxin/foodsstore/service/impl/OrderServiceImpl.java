package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.commons.converter.OrderMaster2OrderDTOConverter;
import com.shuxin.foodsstore.commons.enums.OrderStatusEnum;
import com.shuxin.foodsstore.commons.enums.PayStatusEnum;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.dto.CartDTO;
import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.entity.OrderDetail;
import com.shuxin.foodsstore.entity.OrderMaster;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.repository.order.OrderDetailRepository;
import com.shuxin.foodsstore.repository.order.OrderMasterRepository;
import com.shuxin.foodsstore.service.OrderService;
import com.shuxin.foodsstore.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liu yang
 * @ClassName: OderServiceImpl
 * @Description: TODO(订单服务层.)
 * @date 2017/12/25 11:39
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderMasterRepository masterRepository;


    /**
     * @param [orderDTO]
     * @return com.shuxin.foodsstore.dto.OrderDTO
     * @Title: create 创建订单
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = IDUtils.getUUID();
        BigDecimal priceAmount = new BigDecimal(0);

        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {

            ProductInfo info = infoService.findOne(orderDetail.getProductId());

            if (info == null) {
                throw new MyRuntimeException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 创建计算订单总价

            priceAmount = info.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(priceAmount);

            //详情入库

            orderDetail.setDetailId(IDUtils.getUUID());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(info, orderDetail);

            detailRepository.save(orderDetail);

        }

        //3. 订单写入数据库
        OrderMaster orderMaster = new OrderMaster();

        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(priceAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        masterRepository.save(orderMaster);

        //减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        infoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = masterRepository.findOne(orderId);

        if (orderMaster == null) {
            throw new MyRuntimeException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = detailRepository.findByOrderId(orderMaster.getOrderId());

        if (orderDetailList == null) {
            throw new MyRuntimeException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }


        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenid(String openid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = masterRepository.findByBuyerOpenid(openid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * @Title: cancel 取消订单
     * @param [orderDTO]
     * @return com.shuxin.foodsstore.dto.OrderDTO
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断状态

        // log.info("[Integer 比较] = {}",orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()));
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {

            log.info("[订单状态错误] 错误订单: {}", orderDTO);

            throw new MyRuntimeException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改状态

        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        OrderMaster resultMaster = masterRepository.save(orderMaster);

        if (resultMaster == null) {
            log.info("订单取消失败");
            throw new MyRuntimeException(ResultEnum.ORDER_CANCEL_ERROR);
        }
        //3. 返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new MyRuntimeException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        infoService.increaseStock(cartDTOList);

        //4. 若付款返回金额
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO:返回金额

        }

        return orderDTO;

    }

    /**
     * @Title: finish 结束订单
     * @param [orderDTO]
     * @return com.shuxin.foodsstore.dto.OrderDTO
     */
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {

        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new MyRuntimeException(ResultEnum.ORDER_STATUS_ERROR);
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster resultOrder = masterRepository.save(orderMaster);

        if (resultOrder == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new MyRuntimeException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {

        //1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[此订单已经支付]订单状态不正确 orderId ={} ,orderStatus= {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new MyRuntimeException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2. 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[此订单支付状态错误] orderId ={},payStatus ={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new MyRuntimeException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3. 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster payResult = masterRepository.save(orderMaster);

        if (payResult == null) {
            log.error("[修改支付状态失败] orderMaster", orderMaster);
            throw new MyRuntimeException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = masterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return  new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

}
