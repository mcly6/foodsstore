package com.shuxin.foodsstore.model;

import lombok.Data;
/**
 * @ClassName: IpInfo
 * @Description: ipModel类.   
 * @Author liu yang  
 * @Date 2018/2/1 15:22  
 */ 
@Data
public class IpInfo implements java.io.Serializable {
    /**
     * @Fields serialVersionUID :
     */

    private static final long serialVersionUID = 1L;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String region;
    /**
     * 城市
     */
    private String city;
    /**
     * 运营商
     */
    private String isp;
}


