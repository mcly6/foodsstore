package com.shuxin.foodsstore.repository;

import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.entity.OrderMaster;
import com.shuxin.foodsstore.repository.order.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster master = new OrderMaster();

        master.setOrderId(IDUtils.getUUID());
        master.setBuyerAddress("土屋路1111");
        master.setBuyerId("654321");
        master.setBuyerOpenid("dcba");
        master.setBuyerPhone("98765432123");
        master.setOrderAmount(new BigDecimal(20));

        OrderMaster om = repository.save(master);
        Assert.assertNotNull(om);
    }

    @Test
    public void findByBuyerOpenid() {

        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> orderMasters =  repository.findByBuyerOpenid("abcd",request);

        List<OrderMaster> content = orderMasters.getContent();

        for (OrderMaster master : content) {

            log.info("[所有订单] master = {}",master.toString());

        }
    }

}