package com.shuxin.foodsstore.service;

import com.shuxin.foodsstore.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService infoService;
    @Test
    public void findOne() throws Exception {

        ProductInfo info = infoService.findOne("201702011");

        log.info("info = {}",info);

        Assert.assertNotNull(info);


    }

    @Test
    public void findUpAll() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void upSale() throws Exception {
    }

    @Test
    public void downSale() throws Exception {
    }

    @Test
    public void increaseStock() throws Exception {
    }

    @Test
    public void decreaseStock() throws Exception {
    }

}