package com.shuxin.foodsstore.vo;

import lombok.Data;

@Data
public class ProductNumSessionVO {

    /** 本商品id .*/
    private String id ;
    
    /** 本商品数量 .*/
    private int num = 0 ;
    
    /** 购物车商品总数量 .*/
    private int totalNum = 0;
    
    
    
        

}
