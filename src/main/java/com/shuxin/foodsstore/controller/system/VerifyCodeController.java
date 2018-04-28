package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.enums.*;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.system.SysVerifyCodeEntity;
import com.shuxin.foodsstore.service.system.VerifyCodeService;
import com.shuxin.foodsstore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;


    /**   
     * @Title: 发送验证码
     * @Param phone: 电话号码
     * @Param type: 短信类型
     * @Return
     */ 
    public ResultVO sendVerifyCode(@RequestParam("phone") String phone ,String type) {

        //数据库查询是否有保存数据
        SysVerifyCodeEntity code = verifyCodeService.findByPhone(phone);


        SysVerifyCodeEntity sysVerifycodeEntity = new SysVerifyCodeEntity(phone
                ,IDUtils.getValidateCode(SysVerifyCodeDigitsEnum.REGIST.getCode())
                , SelectEnum.NOT.getCode()
                , SysVerifyCodeTypeEnum.REGISTER.getCode()
                , SysVerifyCodeTimeEnum.REGIST.getCode());

        SysVerifyCodeEntity save = verifyCodeService.save(sysVerifycodeEntity);

        if (save == null) {
            return ResultVOUtils.error(400, ResultEnum.SYSVERIFYCODE_CREATE_FAIL.getMessage());
        }

        /** TODO(调用阿里大于发送) .*/
        return  ResultVOUtils.success();

    }
}
