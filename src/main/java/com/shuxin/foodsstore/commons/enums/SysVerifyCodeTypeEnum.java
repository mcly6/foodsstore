package com.shuxin.foodsstore.commons.enums;


import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

@Getter
public enum SysVerifyCodeTypeEnum implements CodeEnum {

    REGISTER("注册", 1),
    UPDATE_PHONE("修改手机号",2);



    private String msg;
    private Integer code;


    SysVerifyCodeTypeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
