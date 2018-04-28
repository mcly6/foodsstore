package com.shuxin.foodsstore.repository.order;

import com.shuxin.foodsstore.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
* @ClassName: OrderMasterRepository
* @Description: TODO(订单数据操作.)
* @author liu yang  
* @date 2017/12/22 10:15  
*/ 
public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);

    OrderMaster findByBuyerOpenidAndOrderId(String openid, String orderId);

}
