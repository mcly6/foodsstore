package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.repository.product.ProductCategoryRepository;
import com.shuxin.foodsstore.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @ClassName: ProductCategoryServiceImpl
* @Description: TODO(商品分类服务层.)   
* @author liu yang  
* @date 2017/12/25 11:39
*/ 
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll(Sort sort) {

        return categoryRepository.findAll(sort);
    }



    @Override
    public List<ProductCategory> findbyCategorySerialIn(List<Integer> categoryserialList) {
        return categoryRepository.findByCategorySerialIn(categoryserialList);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Map<Integer, List<ProductCategory>> findAllOrderByCategoryType(Sort sort ,Integer categoryModel) {

        List<ProductCategory> categoryList = categoryRepository.findAllByCategoryModel(sort , categoryModel);

        Map<Integer, List<ProductCategory>> map = categoryList.stream().collect(Collectors.groupingBy(ProductCategory::getCategoryType));


        return map;
    }

    @Override
    public List<ProductCategory> findAllBycategomodeAndCategoryModel(Integer categoryModel) {
        return categoryRepository.findAllByCategoryModel(categoryModel);
    }

    @Override
    public List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList) {

        return categoryRepository.findByCategoryIdIn(categoryIdList);
    }


}
