package com.shuxin.foodsstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import com.shuxin.foodsstore.commons.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
* @ClassName: ProductInfo
* @Description: TODO(商品信息.)
* @author liu yang  
* @date 2017/12/21 9:58  
*/ 
@Data
@Entity
@DynamicUpdate
public class ProductInfo implements Serializable{

    @Id
    private String productId;
    /**  商品名称 .*/
    private String productName;
    /**  商品价格 .*/
    private BigDecimal productPrice;
    /**  商品库存 .*/
    private Integer productStock;
    /**  商品说明 .*/
    private String productDescription;
    /**  商品图片 .*/
    private String productIcon;
    /**  商品状态 默认0是上架 1是下架 .*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**  商品类目 关联ProductCategory id .*/
    private Integer categoryType;
        
    /** 是否套餐 0否1是.*/
    private Integer ifCombo;

    /** 当类型为套餐是的信息 .*/
    /** 菜品 .*/
    private String dishes ;

    /** 汤品 .*/
    private String  soupsBroths;

    /** 主食 .*/
    private String stapleFood ;


    /**  创建时间 .*/
   // @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**  修改时间 .*/
   // @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }


}
