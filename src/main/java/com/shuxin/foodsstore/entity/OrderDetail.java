package com.shuxin.foodsstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liu yang
 * @ClassName: OrderDetail
 * @Description: TODO(订单详情.)
 * @date 2017/12/25 9:51
 */
@Entity
@Data
public class OrderDetail {

    /**
     * 详情ID .
     */
    @Id
   /* @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "system_uuid",strategy = "uuid")*/
    private String detailId;

    /**
     * 订单ID .
     */
    private String orderId;

    /**
     * 商品ID .
     */
    private String productId;

    /**
     * 商品名称 .
     */
    private String productName;

    /**
     * 商品价格 .
     */
    private BigDecimal productPrice;

    /**
     * 商品数量 .
     */
    private Integer productQuantity;

    /**
     * 商品图片 .
     */
    private String productIcon;

    /**
     * 创建时间 .
     */
    private Date createTime;

    /**
     * 修改时间 .
     */
    private Date updateTime;


}
