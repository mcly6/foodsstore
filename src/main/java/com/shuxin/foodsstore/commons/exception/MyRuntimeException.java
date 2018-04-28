package com.shuxin.foodsstore.commons.exception;

import com.shuxin.foodsstore.commons.enums.ResultEnum;

/**
 * @author liu yang
 * @ClassName: MyRuntimeException
 * @Description: TODO(自定义运行时错误.)
 * @date 2017/12/21 16:49
 */
public class MyRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;


    /**
     * @param
     * @return
     * @Title: 创建一个新的实例MyException
     */
    public MyRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyRuntimeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = code;
    }
    public MyRuntimeException(String message) {
        super(message);
        this.code = code;
    }
}
