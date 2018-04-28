package com.shuxin.foodsstore.commons.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.entity.OrderDetail;
import com.shuxin.foodsstore.formdata.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderFrom2DTO
 * @Description: order表单转换为dto.
 * @Author liu yang
 * @Date 2017/12/26 10:22
 */
@Slf4j
public class OrderForm2DTO {

    public static OrderDTO convert(OrderForm orderForm) {

        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();

        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerId(orderForm.getName());


        List<OrderDetail> orderDetails ;

        try {

            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.info("[转换参数错误] items ={}", orderForm.getItems());
            throw new MyRuntimeException(ResultEnum.PARAMETER_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;

    }
}
