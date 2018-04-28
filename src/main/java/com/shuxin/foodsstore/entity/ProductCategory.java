package com.shuxin.foodsstore.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory implements Serializable{

    @Id
    @GeneratedValue
    private Integer categoryId ;

    private String categoryName ;

    private Integer categorySerial;
    
    /** 类目分类 1--精致正餐 2-->小炒菜品   .*/
    private Integer categoryType;
    
    /** 类目类别 1--菜品 2--套餐.*/
    private Integer categoryModel;

   // @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


























}














