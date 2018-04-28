package com.shuxin.foodsstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shuxin.foodsstore.commons.enums.OrderStatusEnum;
import com.shuxin.foodsstore.commons.enums.PayStatusEnum;
import com.shuxin.foodsstore.commons.serializer.Date2LongSerializer;
import com.shuxin.foodsstore.commons.utils.EnumUtil;
import com.shuxin.foodsstore.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    /**
     * 主键 .
     */
    private String orderId;

    /**
     * 买家ID .
     */
    private String buyerId = "";

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
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 修改时间 .
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
