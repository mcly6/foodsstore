package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.utils.ServletUtil;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
* @ClassName: AdminController
* @Description: TODO(超管登录.)
* @author liu yang
* @date 2017/12/21 17:12
*/
@Controller
@RequestMapping("/super")
@Slf4j
public class AdminController {

    @Autowired
    private LoginController login;
    /**
    * @Title: 跳转到超管登录页面
    * @param
    * @return
    */
    @RequestMapping(value = "/admin_login")
    public String getAdminLoginPage() {

        if (ServletUtil.isAuthentic()) {// 已经认证
            return "system/admin_index";
        }

        if (SecurityUtils.getSubject().isAuthenticated()) {// 判断当前浏览器中是否存在登录用户,登出当前浏览器中的已登录用户,防止串session
            SecurityUtils.getSubject().logout();
        }
        return "system/admin_login";
    }
    /**
    * @Title: 超管登录
    * @param
    * @return
    */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultVO adminLogin(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        return login.login(user, bindingResult, redirectAttributes, request);
    }

    /**
    * @Title: 跳转到超管页面
    * @param
    * @return
    */
    @RequestMapping(value = "/admin_index")
    public String getAdminIndex() {
        return "system/admin_index";
    }


}
