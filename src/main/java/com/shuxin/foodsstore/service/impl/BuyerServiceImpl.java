package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.service.BuyerService;
import com.shuxin.foodsstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOn(String openid, String orderId) {

        return checkOrder(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrder(openid, orderId);

        if (orderDTO == null) {

            log.info("[订单取消错误] openid ={}, orderId= {}", openid, orderId);
            throw new MyRuntimeException(ResultEnum.ORDER_CANCEL_ERROR);
        }

        return orderDTO;
    }
    
    /** 检测openid 和 订单中的openid 统一openid和订单信息 .*/
    private OrderDTO checkOrder(String openid, String orderId) {

        OrderDTO orderDTO = orderService.findOne(orderId);

        if (orderDTO == null) {
            return null;
        }
        if (!openid.equals(orderDTO.getBuyerOpenid())) {
            log.error("[订单id 和openid 对应错误]");
            throw new MyRuntimeException(ResultEnum.PARAMETER_ERROR);
        }

        return orderDTO;
    }
}
