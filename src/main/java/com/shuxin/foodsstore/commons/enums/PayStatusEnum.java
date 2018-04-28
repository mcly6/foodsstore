package com.shuxin.foodsstore.commons.enums;

import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

/**
 * @author liu yang
 * @ClassName: PayStatusEnum
 * @Description: TODO(支付状态码.)
 * @date 2017/12/22 10:08
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    /**
     * 编码 .
     */
    private Integer code;

    /**
     * 说明 .
     */
    private String message;


    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
