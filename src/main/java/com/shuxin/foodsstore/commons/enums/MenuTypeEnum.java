package com.shuxin.foodsstore.commons.enums;

import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

/**
* @ClassName: MenuTypeEnum
* @Description: TODO(菜单类型.)
* @author liu yang  
* @date 2017/12/22 17:16  
*/
@Getter
public enum MenuTypeEnum implements CodeEnum {
    MENU(0, "菜单"), ACTION(1,"功能"), ;

    /** 编码 .*/
    private Integer code ;
    
    /** 说明 .*/
    private String message ;

    MenuTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
