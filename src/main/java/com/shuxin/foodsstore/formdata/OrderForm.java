package com.shuxin.foodsstore.formdata;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

/**
 * @ClassName: OrderForm
 * @Description: 订单表单信息.
 * @Author liu yang
 * @Date 2017/12/26 10:04
 */
@Data
public class OrderForm {

    /**
     * 姓名 .
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 电话 .
     */
    @NotEmpty(message = "电话不能为空")
    private String phone;

    /**
     * 住址 .
     */
    @NotEmpty(message = "住址不能为空")
    private String address;

    /**
     * openid .
     */
    @NotEmpty(message = "openid不能为空")
    private String openid;

    /**
     * 订单详情列表 .
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;


}
