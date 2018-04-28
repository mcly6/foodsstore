package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum  SelectEnum {
    NOT(0, "否"),YES(1,"是"),OTHER(9,"其他");

    /** 编码 .*/
    private Integer code ;
    
    /** 消息 .*/
    private String msg ;


    SelectEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }
}
