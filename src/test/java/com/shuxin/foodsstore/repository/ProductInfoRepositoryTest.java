package com.shuxin.foodsstore.repository;

import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.repository.product.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository infoRepository;
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = infoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void  save(){
        ProductInfo info = new ProductInfo();
        info.setProductId("201702011");
        info.setProductName("15元营养套餐");
        info.setProductPrice(new BigDecimal(10));
        info.setCategoryType(1);
        info.setProductDescription("精心搭配,营养全面");
        info.setProductIcon("http://www.123.com/xx1.jpg");
        info.setProductStock(100);

        infoRepository.save(info);

    }

    @Test
    public void findTest() {

        ProductInfo info = infoRepository.findOne("1515490089790414236a");

        System.out.println(info.getProductStatusEnum().getMessage());

    }


}