package com.shuxin.foodsstore.service;

import com.shuxin.foodsstore.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOn(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);

}
