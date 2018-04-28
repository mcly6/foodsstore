package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum SysVerifyCodeDigitsEnum {

    REGIST("注册", 6),
    UPDATE_PHONE("修改手机号",6);


    private String name;
    private Integer code;

    SysVerifyCodeDigitsEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
