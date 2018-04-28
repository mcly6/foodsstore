package com.shuxin.foodsstore.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName: ProductCombo
 * @Description: 商品套餐信息.
 * @Author liu yang  
 * @Date 2018/1/8 9:35  
 */ 
@Entity
@Data
@DynamicUpdate
public class ProductCombo implements Serializable{
    
    /** id .*/
    @Id
    private String  id;

    /** 套餐名称 .*/

    private String comboName;

    /** 价格 .*/
    private String  comboPrice;

    /** 数量 .*/
    private Integer comboStock;

    /** 图片 .*/
    private String comboIcon ;

    /** 菜品 .*/
    private String dishes ;

    /** 汤品 .*/
    private String  soupsBroths;

    /** 主食 .*/
    private String stapleFood ;

    /** 创建时间 .*/
    private Date createTime ;
    
    /** 修订时间 .*/
    private Date updateTime ;
    
    


    
    
    
    
    
    

}
