package com.shuxin.foodsstore.commons.utils;

import com.shuxin.foodsstore.commons.configuration.MsgConfig;
import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.service.system.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.List;

@Slf4j
public class ServletUtil implements ServletContextAware {


    @Autowired
    private static ServletContext servletContext;

    /**
     *
     * @description: 设置session
     * @name:  setSession
     * @user:  lucs
     * @date :  2016年1月13日 下午4:34:04
     * @param key
     * @param value
     */
    public static void setSession(Object key, Object value){
        Subject subject = SecurityUtils.getSubject();
        if(null != subject){
            Session session = subject.getSession();
            log.info("{\n    shiro_Session默认超时时间为[" + session.getTimeout() + "]毫秒"
                    + "\n    会话启动时间:"+ DateUtil.date2String( session.getStartTimestamp(),DateUtil.PATTERN_STANDARD)
                    + "\n    会话最后访问时间:"+DateUtil.date2String( session.getLastAccessTime(),DateUtil.PATTERN_STANDARD)+"\n}");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 获取服务器资源路径根目录
     * @return
     */
    public static String getServletContextRealPath(){
        return getServletContext().getRealPath("");
    }

    /**
     * 验证当前用户是通过登录认证
     * @return
     */
    public static boolean isAuthentic(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null && subject.isAuthenticated()){
            return true;
        }
        return false;
    }

    /**
     *  记住我
     * @return
     */
    public static boolean isRemembered(){
        Subject subject = SecurityUtils.getSubject();
        return subject.isRemembered();
    }


    /**
     * 验证当前用户是否含有指定权限
     * @return
     */
    public static boolean isPermitted(String authority){
        try {
            Subject subject = SecurityUtils.getSubject();
            boolean b=subject.isPermitted(authority);
            return  b;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

    /**
     * 获取系统登录用户名,在线唯一标示(规则:user.id + @ + user.Tenant_id)
     * @return
     */
/*    public static String getSysAccount() {
        try {
            User user = getCurrentUser();
            return (user.getUsername() +"@"+ user.getTenantId());
        } catch (Exception e) {
            return MsgConfig.UNRECOGNIZED;
        }
    }*/

    /**
     * 获取当前用户的企业标识Tenantid
     * @return
     */
/*    public static String getCurrentTenantid() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated() || subject.isRemembered()) {
                return getCurrentUser().getTenantId();
            }
        } catch (Exception e) {
            log.error("---------------------------- ServletUtil - tenant_id获取失败！");
            e.printStackTrace();
        }
        return null;
    }*/
    /**
     * 当前用户对象
     */
    public static User getCurrentUser(){
        return (User) getSessionAttribute(MsgConfig.CURRENT_USER);
    }
    /**
     * 获取ApplicationContext
     * @param c
     * @return
     * @return
     */
    public static ApplicationContext getApplicationContext(){

        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }


    /**
     * 获取ServletContext
     * @return
     */
    public static ServletContext getServletContext(){

        return servletContext;
    }

    /**
     * 获取ServletContext保存的数据
     * @param name
     * @return
     */
    public static void setServletContextAttribute(String name,Object object){

        servletContext.setAttribute(name, object);

    }
    /**
     * 获取ServletContext保存的数据
     * @param name
     * @return
     */
    public static Object getServletContextAttribute(String name){

        return servletContext.getAttribute(name);

    }


    /**
     * 获取 Session
     * @return
     */
    public static Session getSession(){

        return SecurityUtils.getSubject().getSession(true);
    }
    /**
     * 获取 Session保存的数据
     * @param name
     * @return
     */
    public static Object getSessionAttribute(String name){

        Session session = getSession();
        if(session == null){
            return null;
        }
        return session.getAttribute(name);

    }



    /**
     * 获取在线用户Map (缓存中获取)
     * @return
     */
    /*public static OnLineUser getOnLineUsersCahe(String key){
		return null!=OnLineUserCache.getItem(key)?(OnLineUser)OnLineUserCache.getItem(key):null;
	}*/


    /**
     * 获取在线用户Map (ServletContext上下文中获取)
     * @return
     */
	/*@SuppressWarnings("unchecked")
	public static Map<String,OnLineUser> getOnLineUsers(){
		return (Map<String,OnLineUser>)getServletContextAttribute("onLineUsers");
	}*/


    /**
     * 获取HttpServletRequest
     * @return
     */
  /*  public static HttpServletRequest getRequest(HttpServletRequest request){
        return request;
        //return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }*/
    /**
     * 获取HttpServletRequest 保存的数据
     * @param name
     * @return
     */
    public static Object getRequestAttribute(String name){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(name);
    }

    /**
     * 判断当前用户是不是开发者
     * @return
     */
    public static boolean isDeveloper(){
        return (Boolean)getSession().getAttribute("dev");
    }
    /**
     * 判断当前用户是不是系统超级管理员
     * @return
     */
    public static boolean isSuperAdmin(){
        return (Boolean)getSession().getAttribute("sa");
        //return false;
    }

    /**
     * 清空缓存中 用户权限认证信息
     * @param userId 用户id
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void removeAuthorizationCache(String userId){
        EhCacheManager ehCacheManager = (EhCacheManager)getApplicationContext().getBean("shiroEhcacheManager");

        Cache cache=ehCacheManager.getCache("shiroAuthRealm.authorizationCache");

        System.out.println("==="+cache.keys()+ehCacheManager.getCacheManager().getCache("shiroAuthRealm.authorizationCache").getSize());

        System.out.println("清空认证缓存"+cache.remove(new SimplePrincipalCollection(userId,"shiroAuthRealm")));
    }




    @Override
    public void setServletContext(ServletContext arg0) {
        servletContext=arg0;
    }

    /**
     *  获取当前用户角色list集合
     */
    public static List<Role> getCurrentUserRoles(){
        User user=getCurrentUser();
        RoleService s = getApplicationContext().getBean(RoleService.class);
        return s.getRolesByUser(user);
    }

    /**
     * 获取当前用户对象
     * @param request
     * @return
     */
	/*public static Member getMember(){

		return(Member)getSessionAttribute(MsgConfig.CURRENT_USER);
	}*/

    /**
     * 获取当前用户角色ids
     * @return
     */
	/*public static List<String> getCurrentUserRoleIds(){
		return getRoleIdsByUserId(getMember().getId());
	}

	/**
	 * 根据用户id获取当前用户角色ids
	 * @param userId
	 * @return
	 */
	/*public static List<String> getRoleIdsByUserId(String userId){

		RoleService roleService=getApplicationContext().getBean(RoleService.class);
		return roleService.selectRolesByUserId(userId);
	}*/


}