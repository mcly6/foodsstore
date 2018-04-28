package com.shuxin.foodsstore.repository;

import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.entity.OrderDetail;
import com.shuxin.foodsstore.repository.order.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;
    private String bb;

    @Test
    public void saveTest() {
        OrderDetail detail = new OrderDetail() ;

        detail.setDetailId(IDUtils.getUUID());
        detail.setOrderId("072a57bcbe0b47a7b3c2299f4e9a5149");
        detail.setProductId("201702010");
        detail.setProductName("10元营养套餐");
        detail.setProductPrice(new BigDecimal(10));
        detail.setProductQuantity(2);
        detail.setProductIcon("http://www.123.com/xx.jpg");

        OrderDetail orderDetail = repository.save(detail);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = repository.findByOrderId("072a57bcbe0b47a7b3c2299f4e9a5149");

        for (OrderDetail orderDetail : byOrderId) {
            log.info("[订单详情] ordreDetail = {}",orderDetail.toString());

        }
    }


}