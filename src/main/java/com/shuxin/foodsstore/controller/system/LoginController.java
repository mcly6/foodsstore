package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.configuration.MsgConfig;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.IpUtil;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.ServletUtil;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.service.system.PermissionService;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName: LoginController
 * @Description: 登录登出控制.   
 * @Author liu yang  
 * @Date 2018/2/1 15:21  
 */
@Controller
@Slf4j
@RequestMapping("/sys/login")
public class LoginController {

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String loginForm(){

        if (ServletUtil.isAuthentic()) {// 已经认证
            return "index";
        }

        if (SecurityUtils.getSubject().isAuthenticated()) {// 判断当前浏览器中是否存在登录用户,登出当前浏览器中的已登录用户,防止串session
            SecurityUtils.getSubject().logout();
        }
        return "login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            //return "redirect:login";
            return ResultVOUtils.error(400,ResultEnum.USER_LOGIN_FAIL.getMessage());
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            log.info("用户名或密码为空! ");
            redirectAttributes.addFlashAttribute("message", "用户名或密码为空!");
            //return "redirect:login";
            return ResultVOUtils.error(400,ResultEnum.USEr_LOGIN_INFO_ERROR.getMessage());
        }

        //验证
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        token.setHost(IpUtil.getIpAddr(request));//ip

        //获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");

            subject.login(token);

            log.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");

            return ResultVOUtils.error(400,ResultEnum.UNKNOWN_ACCOUNT.getMessage());
        }catch(IncorrectCredentialsException ice){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");

            return ResultVOUtils.error(400,ResultEnum.WRONG_PASSWORD.getMessage());
        }catch(LockedAccountException lae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return ResultVOUtils.error(400,ResultEnum.ACCOUNT_LOCKED.getMessage());
        }catch(ExcessiveAttemptsException eae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");
            return ResultVOUtils.error(400,ResultEnum.LOGIN_ACCOUNT_REPEATEDLY_FAILED.getMessage());
        }catch (DisabledAccountException sae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,帐号已经禁止登录");
            return ResultVOUtils.error(400,ResultEnum.LOGIN_ACCOUNT_BANNED.getMessage());
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return ResultVOUtils.error(400,ae.getMessage());
        }
        //验证是否登录成功
        if(subject.isAuthenticated()){
            log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            //把当前用户放入session
            Session session = subject.getSession();

            User tUser = permissionService.findByUsername(username);


            session.setAttribute(MsgConfig.CURRENT_USER,tUser);
            return ResultVOUtils.success(ResultEnum.USER_LOGIN_SUCCESS.getMessage());
        }else{
            token.clear();
            return ResultVOUtils.error(400,ResultEnum.USER_LOGIN_FAIL.getMessage());
        }
    }


    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------");
        return "common/error";
    }



}
