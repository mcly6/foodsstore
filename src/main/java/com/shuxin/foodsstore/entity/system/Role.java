package com.shuxin.foodsstore.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: Role
* @Description: TODO(角色.)
* @author liu yang  
* @date 2017/12/22 15:11  
*/ 
@Entity
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//可以用increment，或者seqence(oracle),identity(mysql,ms sql)
    private Integer id;
    private String name;//角色名称
    private String description;//描述
    private Date createTime;//创建时间
    private Integer whetherDefault;// 是否是系统默认管理员角色（defaultRole）0-是 1-不是
    private String wxMark;


}
