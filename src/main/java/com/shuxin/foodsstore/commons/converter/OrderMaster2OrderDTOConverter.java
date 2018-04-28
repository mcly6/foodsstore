package com.shuxin.foodsstore.commons.converter;

import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liu yang
 * @ClassName: OrderMaster2OrderDTOConverter
 * @Description: TODO(订单 entity 转化为 dto.)
 * @date 2017/12/25 14:53
 */
public class OrderMaster2OrderDTOConverter {


    /**
     * 单个 .
     */
    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * List .
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
