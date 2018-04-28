package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.SysVerifyCodeEntity;
import com.shuxin.foodsstore.vo.ResultVO;

public interface VerifyCodeService {

    //保存
    SysVerifyCodeEntity save(SysVerifyCodeEntity syscode);

    //验证验证码
    boolean checkVerifyCode(String phone,String code,Integer type,Integer effectMinute);

    //修改验证码已用
    SysVerifyCodeEntity updateCodeUse(String phone, Integer type);

    SysVerifyCodeEntity findByPhone(String phone);
    
    /**   
     * @Title: 发送短信
     * @Param   
     * @Return   
     */ 
    ResultVO sendVerifyCode(String phone,  Integer digits ,Integer type ,Integer mins);
}
