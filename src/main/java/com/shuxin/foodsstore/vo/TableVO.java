package com.shuxin.foodsstore.vo;

import lombok.Data;

@Data
public class TableVO<T> {

    /** 总页数 .*/
    private int code ;
    
    /** 说明 .*/
    private String msg ;

    /** 总条数 .*/
    private int count ;
    
    /** 数据 .*/
    private T data ;
    
        
}
