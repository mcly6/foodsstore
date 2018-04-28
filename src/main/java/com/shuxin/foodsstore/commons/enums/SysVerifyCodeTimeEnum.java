package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum  SysVerifyCodeTimeEnum {

    REGIST("注册", 3),
    UPDATE_PHONE("修改手机号",3);


    private String name;
    private Integer code;

    SysVerifyCodeTimeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
