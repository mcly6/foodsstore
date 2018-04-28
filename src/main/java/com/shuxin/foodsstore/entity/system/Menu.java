package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.enums.StatusEnum;
import com.shuxin.foodsstore.commons.enums.MenuTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
* @ClassName: Menu
* @Description: TODO(菜单管理.)
* @author liu yang  
* @date 2017/12/22 15:04  
*/
@Entity
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//可以用increment，或者seqence(oracle),identity(mysql,ms sql)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * 资源路径
     */
    private String uri;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 资源类型(0-菜单，1-功能)
     */
    private Integer type = MenuTypeEnum.MENU.getCode();
    /**
     * 资源序列
     */
    private Integer seq;
    /**
     * 状态(1;启用,0:未启用)
     */
    private Integer status = StatusEnum.START.getCode();
    /**
     * 唯一标示
     */
    private String code;
    /**
     * 资源排序
     */
    private Integer sortno;
    /**
     * 资源备注
     */
    private String remark;

    /**
     * 菜单适用人员(1-系统管理员,2-患者/家属,3-营养科,4-订/送餐员,5-餐厅)多个用;隔开
     */
    private String belong;

}
