package com.shuxin.foodsstore.service.impl;

import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.dto.CartDTO;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.repository.product.ProductInfoRepository;
import com.shuxin.foodsstore.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liu yang
 * @ClassName: ProductInfoServiceImpl
 * @Description: TODO(商品信息服务层.)
 * @date 2017/12/25 11:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository infoRepository;


    /**
     * @param [productId]
     * @return com.shuxin.foodsstore.entity.ProductInfo
     * @Title: findOne 查询单个订单信息
     */
    @Override
    public ProductInfo findOne(String productId) {
        return infoRepository.findOne(productId);
    }

    @Override
    /**
     * @Title: 查询所有上架
     * @param
     * @return
     */
    public List<ProductInfo> findUpAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAllByProductStatusAndCategoryTypeIn(Integer productStatus, List<Integer> categoryList) {
        return infoRepository.findAllByProductStatusAndCategoryTypeIn(productStatus,categoryList);
    }

    @Override
    /**
     * @Title: findAll 分页查询所有
     * @param [pageable]
     * @return org.springframework.data.domain.Page<com.shuxin.foodsstore.entity.ProductInfo>  
     */
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    /**
     * @Title: save 保存
     * @param [productInfo]
     * @return com.shuxin.foodsstore.entity.ProductInfo
     */
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }

    @Override
    /**
     * @Title: onSale 上架商品
     * @param [productId]
     * @return com.shuxin.foodsstore.entity.ProductInfo
     */
    public ProductInfo upSale(String productId) {
        ProductInfo one = infoRepository.findOne(productId);
        one.setProductStatus(ProductStatusEnum.UP.getCode());


        return infoRepository.save(one);
    }

    @Override
    /**
     * @Title: upSale 下架商品
     * @param [productId]
     * @return com.shuxin.foodsstore.entity.ProductInfo
     */
    public ProductInfo downSale(String productId) {
        ProductInfo one = infoRepository.findOne(productId);
        one.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return infoRepository.save(one);
    }

    @Override
    /**
     * @Title: increaseStock 加库存
     * @param [cartDTOList]
     * @return void
     */
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {

            ProductInfo info = infoRepository.findOne(cartDTO.getProductId());

            if (info == null) {
                throw new MyRuntimeException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            info.setProductStock(info.getProductStock() + cartDTO.getProductQuantity());

            infoRepository.save(info);

        }
    }

    @Override
    /**
     * @Title: decreaseStock 减库存
     * @param [cartDTOList]
     * @return void
     */
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {

            ProductInfo info = infoRepository.findOne(cartDTO.getProductId());

            if (info == null) {
                throw new MyRuntimeException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer diff = info.getProductStock() - cartDTO.getProductQuantity();

            if (diff < 0) {
                throw new MyRuntimeException(ResultEnum.PRODUCT_STOCK_ERRO);
            }

            info.setProductStock(diff);

            infoRepository.save(info);

        }
    }

    @Override
    public void deleteInfo(String id) {
        infoRepository.delete(id);
    }

    @Override
    public List<ProductInfo> findAllByCategoryType(Integer categoryType) {
        return infoRepository.findAllByCategoryType(categoryType);
    }

}
