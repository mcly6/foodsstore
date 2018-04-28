package com.shuxin.foodsstore.commons.shiro;

import com.shuxin.foodsstore.commons.utils.EhcacheUtil;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
                                      AuthenticationInfo info) {
        String username = (String) token.getPrincipal();//获取用户名
        Object element = EhcacheUtil.getItem(username);//从缓存中获取username


        long wait_time= EhcacheUtil.getCache().getCacheConfiguration().getTimeToLiveSeconds()/60;//冷却时间

        AtomicInteger retrycount = new AtomicInteger(element!=null?Integer.parseInt(element.toString()):0);	//已经错误次数

        if (retrycount.incrementAndGet() > 2 ) {//超过允许最大次数,并且有限制次数
            throw new AccountException("密码错误次数已达上限，请 "+wait_time+" 分钟后再次尝试 ！");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){//密码认证通过
            EhcacheUtil.removeItem(username);//清除当前用户缓存记录
            //CaptchaCache.remove_code_info(ThreadLocalContent.getRequest());//移除验证码相关信息
            return true;
        }else{//密码没验证通过
            if (element == null) {
                element = 1;
                EhcacheUtil.putItem(username, element);//次数 +1
            } else {
                element=Integer.parseInt(element.toString()) + 1;//次数+1
                EhcacheUtil.putItem(username,element);
            }

            int i= Integer.parseInt(element.toString());//已经错误次数
            int j=4-i;//剩余机会(允许错误次数-已错误次数)
            if(j>0){
                throw new AccountException("密码输入错误 , 剩余 "+j+" 次机会 .");
            }else{
                throw new AccountException("密码错误次数已达上限，请"+wait_time+" 分钟后再次尝试 ！");
            }
        }

    }

}