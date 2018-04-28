package com.shuxin.foodsstore.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
* @ClassName: RoleMenu
* @Description: TODO(角色资源.)   
* @author liu yang  
* @date 2017/12/22 15:12  
*/
@Data
@Entity
public class RoleMenu implements Serializable {


    /** 主键id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//可以用increment，或者seqence(oracle),identity(mysql,ms sql)
    private int id;

    /** 角色id */
    private Integer roleId;

    /** 资源id */
    private Integer menuId;
}
