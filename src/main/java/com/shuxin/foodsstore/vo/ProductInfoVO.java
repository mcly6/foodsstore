package com.shuxin.foodsstore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    /** 是否是套餐 .*/
    @JsonProperty("ifCombo")
    private Integer ifCombo;


    /** 商品下单数量 .*/
    @JsonProperty("num")
    private int  num = 0;
    
    /** 购物车商品总数量 .*/
    @JsonProperty("totalNum")
    private int totalNum = 0 ;
    /** 五星评价数量 .*/
    @JsonProperty("starNum")
    private String starNum;


}
