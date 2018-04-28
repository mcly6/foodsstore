package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.enums.UserTypeEnum;
import com.shuxin.foodsstore.commons.utils.MD5Util;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.formdata.RegisterForm;
import com.shuxin.foodsstore.service.system.UserService;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName: RegisterController
 * @Description: 用户注册.
 * @Author liu yang
 * @Date 2017/12/27 15:13
 */
@RestController
@RequestMapping("/register")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginController login;

    @PostMapping("/patientRegister")
    public ResultVO register(@Valid RegisterForm registerForm , BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.USER_REGISTER_ERROR.getMessage());
        }

        //1. 验证验证码
        //TODO("暂时验证")

        User resultUser = userService.getUser(registerForm.getUsername(), registerForm.getPassword(), UserTypeEnum.PATIENT.getCode());

        if (!(resultUser == null)) {
            return ResultVOUtils.error(400, ResultEnum.USER_ALREADY_EXIST.getMessage());
        }
        //密码加密
        String md5Pwd = MD5Util.getMD5(registerForm.getPassword());


        //2. 存入数据库
        User registerUser = userService.register(registerForm.getUsername(), md5Pwd, UserTypeEnum.PATIENT.getCode());

        if (registerUser == null) {
            return ResultVOUtils.error(400, ResultEnum.USER_REGISTER_ERROR.getMessage());
        } else {
            //替换为加密前的密码
            //registerUser.setPassword(registerForm.getPassword());
            //默认注册登录
            /*
            ResultVO resultVO = this.login.login(registerUser, bindingResult, null, request);

            if (resultVO != null) {

                return resultVO;
            } else {
                return ResultVOUtils.error(400, ResultEnum.USER_LOGIN_FAIL.getMessage());
            }*/
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(registerForm.getUsername());
            token.setPassword(registerForm.getPassword().toCharArray());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            return ResultVOUtils.success() ;



        }

    }

    /**
     * @Title: 验证用户名是否被注册过
     * @Param
     * @Return
     */
    @PostMapping("/validuser")
    public ResultVO validUser(@RequestParam("username") String username) {

        boolean reslutFlag = userService.findByUserName(username);

        if (reslutFlag) {
            return ResultVOUtils.error(400, ResultEnum.USER_ALREADY_EXIST.getMessage());
        } else {
            return ResultVOUtils.success(ResultEnum.USER_NOT_REGISTER);
        }



    }


}
