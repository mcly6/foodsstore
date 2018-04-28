package com.shuxin.foodsstore.model;

import java.util.List;

public class MenuNode {

    /** 标题 .*/
    private String title ;
    
    /** 图标 支持 .*/
    private String icon ;
    
    /** 地址 .*/
    private String href ;
    
    /** 是否展开 .*/
    private boolean spread;

    /** 子项 .*/
    List<MenuNode> children;
}
