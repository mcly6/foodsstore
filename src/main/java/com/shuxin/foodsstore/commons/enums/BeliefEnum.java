package com.shuxin.foodsstore.commons.enums;

import lombok.Getter;

@Getter
public enum BeliefEnum {

    DEFUAL(0, "无信仰"),
    BUDDHISM(1, "佛教"),
    ISLAM(2, "伊斯兰教"),
    OTHER(9, "其他");


    /**
     * .
     */
    private Integer code;

    /**
     * .
     */
    private String msg;

    BeliefEnum(Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }
}
