package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService categoryService;


    @Test
    public void strTest() {

        String filePath = "D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\80b5c3b2-168e-4102-acf9-9cf7ce8f832b.jpg";

        String result = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

        System.out.println(result);

    }

    @Test
    public void findbyCategorySerialIn() {
        List<Integer> typeList = new ArrayList<>();

        typeList.add(10);
      /*  typeList.add(2);
        typeList.add(6);
        typeList.add(8);
        typeList.add(2);*/
        List<ProductCategory> list = categoryService.findbyCategorySerialIn(typeList);

        for (ProductCategory category : list) {

            System.out.println(category.getCategoryName());
        }


    }

}