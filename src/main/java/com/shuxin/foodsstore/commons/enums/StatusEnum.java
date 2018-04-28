package com.shuxin.foodsstore.commons.enums;

import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

/**
 * @author liu yang
 * @ClassName: StatusEnum
 * @Description: TODO(菜单启用装通.)
 * @date 2017/12/22 17:12
 */
@Getter
public enum StatusEnum implements CodeEnum {
    STOP(0, "未启用"),
    START(1, "启用"),;


    /**
     * 编码 .
     */
    private Integer code;

    /**
     * 说明 .
     */
    private String messag;

    StatusEnum(Integer code, String messag) {
        this.code = code;
        this.messag = messag;
    }
}
