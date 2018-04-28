package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: AuthCodeController
 * @Description: 验证码相关.
 * @Author liu yang  
 * @Date 2018/1/16 17:20  
 */ 
@Controller
@RequestMapping("/authCode")
@Slf4j
public class AuthCodeController {


    @RequestMapping("/sendCode")
    @ResponseBody
    public ResultVO sendCode(@RequestParam("username") String username) {



        return ResultVOUtils.success();
    }
}
