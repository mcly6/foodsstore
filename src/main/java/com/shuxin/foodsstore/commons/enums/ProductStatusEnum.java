package com.shuxin.foodsstore.commons.enums;

import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;
/**
* @ClassName: productStatusEnum
* @Description: TODO(商品状态码.)
* @author liu yang  
* @date 2017/12/22 10:14
*/ 
@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;


    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

}
