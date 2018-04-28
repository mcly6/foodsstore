package com.shuxin.foodsstore.entity;

import com.shuxin.foodsstore.commons.enums.OrderStatusEnum;
import com.shuxin.foodsstore.commons.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liu yang
 * @ClassName: OrderMaster
 * @Description: TODO(订单.)
 * @date 2017/12/22 9:26
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster implements  Serializable{

    /**
     * 主键 .
     */
    @Id
    private String orderId ;

    /**
     * 买家ID .
     */
    private String buyerId ;

    /**
     * 买家电话 .
     */
    private String buyerPhone;

    /**
     * 买家地址 .
     */
    private String buyerAddress;

    /**
     * 买家微信openid .
     */
    private String buyerOpenid;

    /**
     * 订单总金额 .
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 .
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态 .
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间 .
     */
    private Date createTime;

    /**
     * 修改时间 .
     */
    private Date updateTime;



}
