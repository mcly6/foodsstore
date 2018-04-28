package com.shuxin.foodsstore.repository.product;

import com.shuxin.foodsstore.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findAllByCategoryType(Integer categoryType);

    List<ProductInfo> findAllByProductStatusAndCategoryTypeIn(Integer productStatus, List<Integer> categoryList);
}
