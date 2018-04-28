package com.shuxin.foodsstore.entity.system;


import com.shuxin.foodsstore.repository.system.DictTypeGroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictTypeGroupTest {

    @Autowired
    private DictTypeGroupRepository repository;

    @Test
    public void saveTest() {
        DictTypeGroup group = new DictTypeGroup();
        group.setTypegroupname("父级类");
        group.setTypegroupcode("001");

        repository.save(group);

    }

    @Test
    public void bigdecimalTest() {
        BigDecimal a = new BigDecimal("0");

        System.out.println(a);
    }
}