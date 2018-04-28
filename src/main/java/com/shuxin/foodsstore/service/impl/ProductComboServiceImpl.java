package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import com.shuxin.foodsstore.entity.ProductCombo;
import com.shuxin.foodsstore.repository.product.ProductComboRepository;
import com.shuxin.foodsstore.service.ProductComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductComboServiceImpl implements ProductComboService {

    @Autowired
    private ProductComboRepository comboRepository;


    @Override
    public ProductCombo save(ProductCombo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Page<ProductCombo> findAll(Pageable pageable) {

        return comboRepository.findAll(pageable);

    }

    @Override
    public ProductCombo findOne(String id) {
        return comboRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        comboRepository.delete(id);
    }


}
