package com.shuxin.foodsstore.commons.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "superAdmin")
@Component
public class SuperAdmin {

    /** name .*/
    private String saName ;

    /** pwd .*/
    private String saPwd ;

    /** ip .*/
    private String saAllowIp ;
    
    /** 路径 .*/
    private String adminPage ;


    
    

}
