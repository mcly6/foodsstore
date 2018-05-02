package com.shuxin.foodsstore.service;

import com.shuxin.foodsstore.entity.ProductCategory;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll(Sort sort);

    List<ProductCategory> findbyCategorySerialIn(List<Integer> categoryserialList);

    ProductCategory save(ProductCategory category);

    void delete(Integer id);

    Map<Integer, List<ProductCategory>> findAllOrderByCategoryType(Sort categorySerial ,Integer categoryModel);

    List<ProductCategory> findAllBycategomodeAndCategoryModel(Integer categoryModel);

    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);
}
