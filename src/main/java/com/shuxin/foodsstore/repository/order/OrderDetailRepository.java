package com.shuxin.foodsstore.repository.order;

import com.shuxin.foodsstore.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* @ClassName: OrderDetailRepository
* @Description: TODO(订单详情操作.)
* @author liu yang  
* @date 2017/12/25 10:13  
*/ 
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}