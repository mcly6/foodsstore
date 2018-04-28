package com.shuxin.foodsstore.vo;

import lombok.Data;

/**
 * 请求返回值
 * @param <T>
 */
@Data
public class ResultVO<T> {

    private Integer code ;

    private String msg;

    private T data;
}
