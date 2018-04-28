package com.shuxin.foodsstore.service;

import com.shuxin.foodsstore.entity.ProductCombo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductComboService {
    ProductCombo save(ProductCombo combo);

    Page<ProductCombo> findAll(Pageable pageable);

    ProductCombo findOne(String id);

    void delete(String id);

}
