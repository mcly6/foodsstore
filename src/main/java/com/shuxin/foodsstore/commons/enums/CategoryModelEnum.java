package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum  CategoryModelEnum {
    CUISINE(1,"菜品"),
    COMBO(2,"套餐");

    /** code .*/
    private Integer code ;
    
    /** msg .*/
    private String msg ;

    CategoryModelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
