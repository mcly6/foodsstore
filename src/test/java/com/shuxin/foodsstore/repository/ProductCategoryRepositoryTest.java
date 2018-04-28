package com.shuxin.foodsstore.repository;

import com.shuxin.foodsstore.commons.enums.CategoryModelEnum;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.repository.product.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Test
    public void findProductCategoriesByCategorySerial() throws Exception {
    }


    @Test
    public void save() {

        ProductCategory category = new ProductCategory();
        category.setCategoryName("早餐");
        category.setCategorySerial(1);

        categoryRepository.save(category);
    }

    @Test
    public void sortTest() {

        List<ProductCategory> categorySerial = categoryRepository.findAllByCategoryModel(new Sort(Sort.Direction.ASC, "categorySerial"), CategoryModelEnum.CUISINE.getCode());

        for (ProductCategory category : categorySerial) {
            System.out.println(category.getCategoryId());

        }


    }

}