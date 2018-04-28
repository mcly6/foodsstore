package com.shuxin.foodsstore.controller.sell;


import com.shuxin.foodsstore.commons.converter.OrderForm2DTO;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.dto.OrderDTO;
import com.shuxin.foodsstore.formdata.OrderForm;
import com.shuxin.foodsstore.service.BuyerService;
import com.shuxin.foodsstore.service.OrderService;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BuyerController
 * @Description: 买家接口.
 * @Author liu yang
 * @Date 2017/12/26 9:59
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * @Title: create 创建订单.
     * @Param [orderForm, bindingResult]
     * @Return com.shuxin.foodsstore.vo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @PostMapping("/create")
    public ResultVO<Map<String, Object>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        //判断表单
        if (bindingResult.hasErrors()) {
            log.error("[提交表单有错误] orderForm ={}", orderForm);
            throw new MyRuntimeException(ResultEnum.ORDER_FORM_ERRO.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        //转化为dto 数据
        OrderDTO orderdto = OrderForm2DTO.convert(orderForm);

        //创建订单

        OrderDTO createResult = orderService.create(orderdto);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtils.success(map);
    }


    /**
     * @Title: list 订单列表.
     * @Param [openid, page, size]
     * @Return com.shuxin.foodsstore.vo.ResultVO<com.shuxin.foodsstore.dto.OrderDTO>
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {

        if (StringUtils.isEmpty(openid)) {
            log.error("[openid]不存在,openid不能为空");
            throw new MyRuntimeException(ResultEnum.PARAMETER_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findByBuyerOpenid(openid, pageRequest);

        return ResultVOUtils.success(orderDTOPage.getContent());
    }


    /**
     * @Title: detail 订单详情
     * @Param [openid, orderId]
     * @Return com.shuxin.foodsstore.vo.ResultVO<com.shuxin.foodsstore.dto.OrderDTO>
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.findOrderOn(openid, orderId);

        return ResultVOUtils.success(orderDTO);


    }

    /**
     * @Title: cancl  取消订单
     * @Param [openid, orderId]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @PostMapping("/cancel")
    public ResultVO cancelOrder(@RequestParam("openid") String openid,
                                @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);

        return ResultVOUtils.success();

    }

}
