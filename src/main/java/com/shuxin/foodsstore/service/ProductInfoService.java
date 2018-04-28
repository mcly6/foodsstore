package com.shuxin.foodsstore.service;

import com.shuxin.foodsstore.dto.CartDTO;
import com.shuxin.foodsstore.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    //查询单个商品
    ProductInfo findOne(String productId);

    //查询所有上架商品
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAllByProductStatusAndCategoryTypeIn(Integer productStatus, List<Integer> categoryList);

    //分页查询所有
    Page<ProductInfo> findAll(Pageable pageable);

    //保存
    ProductInfo save(ProductInfo productInfo);

    //上架
    ProductInfo upSale(String productId);

    //下架
    ProductInfo downSale(String productId);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    void deleteInfo(String id);


    List<ProductInfo> findAllByCategoryType(Integer id);


}
