package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.UserUtils;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.entity.system.UserDietaryHabitInfo;
import com.shuxin.foodsstore.service.system.UserDietaryHabitInfoService;
import com.shuxin.foodsstore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @ClassName: UserDietaryHabitInfoController
 * @Description: 用户饮食是习惯信息控制层.   
 * @Author liu yang  
 * @Date 2018/1/17 15:40  
 */
@Controller
@RequestMapping("/dietary")
public class UserDietaryHabitInfoController {

    @Autowired
    private UserDietaryHabitInfoService infoService;


    /**
     * @Title: adddietary 添加用户饮食习惯信息
     * @Param [info, bindingResult]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/addDietary")
    @ResponseBody
    public ResultVO addDietary(@Valid UserDietaryHabitInfo info, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.USER_DIETARY_ADD_FAIL.getMessage());
        }

        User user = UserUtils.getUser();

        info.setUserId(user.getId());
        info.setId(IDUtils.getUUID());

        UserDietaryHabitInfo infoResult = infoService.save(info);

        if (infoResult == null) {

            return ResultVOUtils.error(400, ResultEnum.USER_DIETARY_ADD_FAIL.getMessage());

        }
        return ResultVOUtils.success();

    }
}
