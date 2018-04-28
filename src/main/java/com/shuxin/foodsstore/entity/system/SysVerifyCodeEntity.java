package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.base.IdEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author zhangdaihao
 * @version V1.0
 * @Title: Entity
 * @Description: 短信验证码
 * @date 2017-08-07 16:39:35
 */
@Entity
@Table(name = "sys_verifycode", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@Data
public class SysVerifyCodeEntity extends IdEntity implements Serializable {
    /**
     * 手机号
     */
    private java.lang.String phone;
    /**
     * 验证码
     */
    private java.lang.String code;
    /**
     * 是否已使用
     */
    private java.lang.Integer isuse;
    /**
     * 验证码类型，如登录、注册
     */
    private java.lang.Integer type;
    /**
     * 有效时长，单位分钟，0为无限制
     */
    private java.lang.Integer effectMinute;
    /**
     * endTime
     */
    private java.util.Date endTime;
    /**
     * 创建时间
     */
    private java.util.Date createTime;


    public SysVerifyCodeEntity() {

    }
    

    public SysVerifyCodeEntity(String phone, String code, Integer isuse, Integer type, Integer effectMinute) {
        super();
        this.phone = phone;
        this.code = code;
        this.isuse = isuse;
        this.type = type;
        this.effectMinute = effectMinute;

    }


}
