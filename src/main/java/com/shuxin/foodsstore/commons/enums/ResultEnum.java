package com.shuxin.foodsstore.commons.enums;


import com.shuxin.foodsstore.commons.base.CodeEnum;
import lombok.Getter;

/**
 * @author liu yang
 * @ClassName: ResultEnum
 * @Description: TODO(返回信息.)
 * @date 2017/12/25 9:10
 */
@Getter
public enum ResultEnum implements CodeEnum {
    /**
     * 商品相关 .
     */
    PRODUCT_NOT_EXIST(40, "商品部存在"),
    PRODUCT_STOCK_ERRO(41, "商品库存错误"),
    PRODUCT_STATUS_EDIT_FAIL(42, "商品修改状态失败"),
    CATEGORY_PRODUCT_EXIST(42, "类目下有商品关联"),

    /**
     * 订单相关 .
     */
    ORDER_NOT_EXIST(41, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(42, "订单详情不存在"),
    ORDER_STATUS_ERROR(43, "订单状态错误"),
    ORDER_CANCEL_ERROR(44, "订单取消错误"),
    ORDER_DETAIL_EMPTY(45, "订单内无详情"),
    ORDER_UPDATE_FAIL(46, "订单更新失败"),
    /**
     * 支付相关 .
     */
    ORDER_PAY_ERROR(47, "支付状态着物"),

    ORDER_FORM_ERRO(48, "订单表格提交错误"),

    PARAMETER_ERROR(49, "参数错误"),

    /**
     * 用户相关
     */
    USER_REGISTER_ERROR(50, "注册失败 !!!"),
    USER_ALREADY_EXIST(51, "用户已注册 !!!"),
    USER_NOT_REGISTER(52, "用户未注册"),
    USEr_LOGIN_INFO_ERROR(53, "用户账户或密码错误"),
    USER_LOGIN_FAIL(54, "用户登录失败"),
    USER_LOGIN_SUCCESS(55, "用户登录成功"),
    USER_NOT_EXIST(56, "用户未登录"),
    USRENAME_ALREADY_EXIST(57, "此电话号码已注册!!!"),
    USER_HOSNUM_FAIL(58, "用户住院号绑定失败"),
    FILE_UPLOAD_ERROR(60, "文件上传失败"),


    /**
     * 商品相关
     */
    PRODUCT_FORM_SUBMIT_ERROR(70, "商品表单提交错误"),
    CATEGORY_FORM_ERROR(71, "商品类目表单提交错误"),
    PRODUCT_COMBO_FORM_ERRO(72, "商品套餐表单提交错误"),
    PRODUCT_COMBO_NOT_EXIST(73, "商品套餐不存在"),
    PRODUCT_COMBO_DELETE_FAIL(73, "商品套餐删除失败"),

    SAVE_FAIL(90, "保存失败"),

    USER_DIETARY_ADD_FAIL(80, "用户饮食习惯信息添加失败"),

    DICTTYPE_FORM_INFO_ERROR(90, "字典类型添加表单信息错误"),
    DICTTYPE_SAVE_FAIL(91,"字典类型添加失败"),
    DICTTYPE_DELETE_SUCCESS(92, "字典类型删除成功"),
    DICTTYPE_DELETE_FAIL(93, "字典类型删除失败"),
    DICTTYPE_FORM_ERROR(94, "字典信息错误"),
    DICTTYPE_INFO_ERROR(95, "字典数据错误"),

    SYSVERIFYCODE_CREATE_FAIL(100, "手机验证码发送失败"),
    SYSVERIFYCODE_SEND_FAIL(101, "手机验证码发送失败"),
    SYSVERIFYCODE_NOT_OUT(102, "验证码未过期"),



    WECHAT_MP_ERROR(110, "微信公众账号方面错误"),

    DATA_TRANSMISSION_FAILURE(120, "数据传输失败"),

    /** 账号登录信息 .*/
    UNKNOWN_ACCOUNT(130,"未知账户"),
    WRONG_PASSWORD(131,"密码不正确"),
    ACCOUNT_LOCKED(132,"账户已锁定"),
    LOGIN_ACCOUNT_REPEATEDLY_FAILED(133,"用户名或密码错误次数大于5次,账户已锁定"),
    LOGIN_ACCOUNT_BANNED(134,"帐号已经禁止登录"),
    ACCOUNT_USERNAME_OR_PASSWORD_NOT_CORRECT(135,"用户名或密码不正确"),
    ACCOUNT_STATUS_EDIT_FAIL(136,"账户状态修改失败"),


    ;


    /**
     * 编码 .
     */
    private Integer code;
    /**
     * 说明 .
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
