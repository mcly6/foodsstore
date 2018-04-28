package com.shuxin.foodsstore.formdata;

import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductComboFrom {

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



}
