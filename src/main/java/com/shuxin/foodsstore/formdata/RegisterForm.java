package com.shuxin.foodsstore.formdata;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class RegisterForm {

    /** 用户名 .*/
    @NotEmpty(message = "请填写6~16位用户名")
    private String username ;
    
    /** 验证码 .*/
    @NotEmpty(message = "请填写验证码")
    private String authcode ;
    
    /** 密码 .*/
    @NotEmpty(message = "请填写8~16位密码")
    private String password ;
    
    /** 确认密码 .*/
    @NotEmpty(message = "请再次填写8~16位密码")
    private String repassword ;
}
