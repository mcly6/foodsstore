package com.shuxin.foodsstore.commons.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 *
 * 类名:  EhcacheUtil
 * 功能:  ehcache的缓存工具类
 * 详细:
 * 所属包: com.yb.em.commons.utils
 * 作者:  lucs
 * 版本:  1.0
 * 日期 :  2014年11月14日 上午8:51:00
 *
 */
@Slf4j
public final class EhcacheUtil {

    //从classes目录查找ehcache.xml配置文件
    private static final CacheManager cacheManager = CacheManager.getInstance();

    /**
     * 创建ehcache缓存
     * 注:(此处不能动态追加,需加入配置文件,原因:现象猜测由于shiro在没有确定用户身份前不让追加)
     */
	/*private static Cache cache = new Cache(
			 new CacheConfiguration()
			.name("sys_pwd_error")
			.eternal(false)
			.maxBytesLocalHeap(5, MemoryUnit.MEGABYTES) //5M
			.maxBytesLocalOffHeap(30, MemoryUnit.MEGABYTES) //30M
			.timeToIdleSeconds(BaseConfig.webconfig.getPwdErrorTime() * 60)
			.timeToLiveSeconds(BaseConfig.webconfig.getPwdErrorTime() * 60));*/
    //timeoutMillis当发送一条消息给监听器时，客户套接字保持打开状态的时间，以毫秒计。默认为120000ms。

    private static Cache cache = cacheManager.getCache("sys_pwd_error");

    static {
        log.info("BBBBBBBBBBBBBBBB默认名称缓存管理器名称:"+CacheManager.DEFAULT_NAME);
        //cacheManager.addCache(cache);
        for (String cacheName : cacheManager.getCacheNames()) {
            log.info("AAAAAAAAAAAAAAAAA  缓存对象名称"+cacheName);
        }
    }

    /**
     *
     * 功能:  添加记录
     * 名称:  putItem
     * 作者 :  lucs
     * 时间 :  2015年3月13日 上午9:28:02
     * 参数:  @param key
     * 参数:  @param item
     */
    public static void putItem(String key, Object item) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
        Element element = new Element(key, item);
        cache.put(element);
    }

    /**
     *
     * 功能:  删除缓存
     * 名称:  removeItem
     * 作者 :  lucs
     * 时间 :  2015年3月13日 上午9:28:13
     * 参数:  @param key
     */
    public static void removeItem(String key) {
        cache.remove(key);
    }

    /**
     *
     * 功能:  更新缓存
     * 名称:  updateItem
     * 作者 :  lucs
     * 时间 :  2015年3月13日 上午9:27:26
     * 参数:  @param key
     * 参数:  @param value
     */
    public static void updateItem(String key, Object value) {
        putItem(key, value);
    }
    /**
     *
     * 功能:  获取记录
     * 名称:  getItem
     * 作者 :  lucs
     * 时间 :  2015年3月13日 上午9:28:36
     * 参数:  @param key
     * 参数:  @return
     */
    public static Object getItem(String key) {
        Element element = cache.get(key);
        if (null != element) {
            return element.getObjectValue();
        }
        return null;
    }

    /**
     *
     * 功能:  返回cache对象
     * 名称:  getCache
     * 作者 :  lucs
     * 时间 :  2015年3月28日 下午2:45:22
     * 参数:  @return
     */
    public  static Cache getCache(){
        return cache;
    }
}