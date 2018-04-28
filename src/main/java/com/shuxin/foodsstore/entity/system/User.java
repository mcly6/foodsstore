package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户信息
 */
@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//可以用increment，或者seqence(oracle),identity(mysql,ms sql)
    private  Integer id;
    /**用户名称.*/
    private  String username;
    /**用户密码.*/
    private String password ;
    /**微信账号.*/
    private String openid;
    /**用户类型.*/
    private Integer type;
    /** 住院号ID .*/
    private String hosNum ;
    /** 昵称 .*/
    private String nickname ;
    /**  状态 0--停用 1--启用.*/
    private Integer status = StatusEnum.START.getCode();

    /** 用户余额 .*/
    private BigDecimal balance  = new BigDecimal("0.00") ;


    
    
    
    

}
