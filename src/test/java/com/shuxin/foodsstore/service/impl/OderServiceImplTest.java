package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.entity.OrderDetail;
import com.shuxin.foodsstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CNPJ;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OderServiceImplTest {

    public static final String USER_OPENID = "abc";


    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(IDUtils.getUUID());
        orderDTO.setBuyerId("712220");
        orderDTO.setBuyerAddress("土屋路 ");
        orderDTO.setBuyerOpenid(USER_OPENID);
        orderDTO.setBuyerPhone("12345678909");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail detail = new OrderDetail();
        detail.setProductId("201702010");
        detail.setProductQuantity(2);

        OrderDetail detail1 = new OrderDetail();

        detail1.setProductId("201702011");
        detail1.setProductQuantity(3);

        orderDetailList.add(detail);
        orderDetailList.add(detail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("[创建订单] result={}", result);

        Assert.assertNotNull(result);


    }

    @Test
    public void findOne() throws Exception {

        OrderDTO orderDTO = orderService.findOne("ddc1556350ec4fa997c1b0878f90d184");

        log.info("[查询订单信息] orderDTO = {}", orderDTO);
    }

    /**
     * 查询订单列表 .
     */
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0, 2);

        Page<OrderDTO> orderDTOPage = orderService.findByBuyerOpenid(USER_OPENID, request);

        log.info("[分页信息] ordereDTOPage ={}", orderDTOPage.getContent());

        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() throws Exception {

        OrderDTO orderDTO = orderService.findOne("ddc1556350ec4fa997c1b0878f90d184");

        OrderDTO cancelDTO = orderService.cancel(orderDTO);

        log.info("退款 cancel:", cancelDTO);
        Assert.assertNotNull(cancelDTO);

    }

    @Test
    public void finish() throws Exception {

        OrderDTO orderDTO = orderService.findOne("ddc1556350ec4fa997c1b0878f90d184");

        OrderDTO finishDTO = orderService.finish(orderDTO);
        log.info("结束 =={}",finishDTO);

        Assert.assertNotNull(finishDTO);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("ddc1556350ec4fa997c1b0878f90d184");
        OrderDTO payDTO = orderService.paid(orderDTO);
        Assert.assertNotNull(payDTO);
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);

        Page<OrderDTO> pageList = orderService.findList(request);

        log.info("[List信息 = {}]",pageList.getContent());
        Assert.assertNotNull(pageList);


    }

}