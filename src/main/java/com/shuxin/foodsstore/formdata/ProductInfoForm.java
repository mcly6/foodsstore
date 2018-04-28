package com.shuxin.foodsstore.formdata;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoForm {

    private String productId;
    /**  商品名称 .*/
    private String productName;
    /**  商品价格 .*/
    private BigDecimal productPrice;
    /**  商品库存 .*/
    private Integer productStock;
    /**  商品说明 .*/
    private String productDescription;
    /**  商品图片 .*/
    private String productIcon;
    /**  商品状态 默认0是上架 1是下架 .*/
    private Integer productStatus ;

    /**  商品类目 关联ProductCategory id .*/
    private Integer categoryType;


}
