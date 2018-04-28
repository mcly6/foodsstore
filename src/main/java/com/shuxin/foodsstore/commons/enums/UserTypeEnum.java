package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    PATIENT(1,"患者"),
    PHYSICIAN(2,"医师"),
    CANTEEN (3,"食堂"),



    ADMINISTRATOR(99,"管理员");

    /** 编码 .*/
    private Integer code ;
    
    /** 说明 .*/
    private String message ;

    UserTypeEnum(Integer code, String message) {

        this.code = code;
        this.message = message;
    }
}
