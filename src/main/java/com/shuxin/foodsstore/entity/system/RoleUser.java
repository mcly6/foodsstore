package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.base.IdEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
/**
* @ClassName: RoleUser
* @Description: TODO(用户角色.)
* @author liu yang  
* @date 2017/12/22 15:18  
*/
@Data
@Entity
public class RoleUser extends IdEntity implements Serializable {

    /** 用户id */
    private Integer userId;

    /** 角色id */
    private Integer roleId;

}
