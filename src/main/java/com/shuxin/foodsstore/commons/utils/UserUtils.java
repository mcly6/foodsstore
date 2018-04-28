package com.shuxin.foodsstore.commons.utils;

import com.shuxin.foodsstore.commons.configuration.BaseConfig;
import com.shuxin.foodsstore.entity.system.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserUtils
 * @Description: session 用户工具类.
 * @Author liu yang
 * @Date 2018/1/17 11:19
 */
public class UserUtils {

    /**
     * @Title: 将用户对象放入session中
     * @Param User 用户对象
     * @Param HttpSession
     * @Return
     */

    public static void setUser(User userInfo, HttpSession session) {
        session.setAttribute(BaseConfig.LOGIN_USER, userInfo);
    }

    /**
     * @Title: 从当前session 中获取用户对象
     * @Param User 用户对象
     * @Return
     */
    public static User getUser(HttpSession session) {
        return (User) session.getAttribute(BaseConfig.LOGIN_USER);
    }
    /**
     * @Title: 从session 中获取用户对象
     * @Param User 用户对象
     * @Return
     */
    public static User getUser() {
        return (User) getsession().getAttribute(BaseConfig.LOGIN_USER);
    }
    
    /**   
     * @Title: 获取session
     * @Param   
     * @Return   
     */ 
    public static HttpSession getsession() {
        return getRequest().getSession();
    }

    /**   
     * @Title: 获取当前对话 请求
     * @Param   
     * @Return   
     */ 
    public static HttpServletRequest getRequest() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        return request;
    }
}
