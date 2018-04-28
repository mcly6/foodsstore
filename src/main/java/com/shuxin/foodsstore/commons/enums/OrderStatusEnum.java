package com.shuxin.foodsstore.commons.enums;

import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

/**
* @ClassName: OrderStatusEnum
* @Description: TODO(订单状态码.)
* @author liu yang  
* @date 2017/12/22 10:14
*/ 
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0,"新订单"),
    FINISHED(1,"结束订单"),
    CANCEL(2,"取消订单")
    ;


    /**
     * 编码 .
     */
    private Integer code;

    /**
     * 说明 .
     */
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
