package com.shuxin.foodsstore.model;

import lombok.Data;

/**
 * @ClassName: TreeNode
 * @Description: 节点树形模型类.
 * @Author liu yang  
 * @Date 2018/1/19 16:04
 */
@Data
public class TreeNode {
   //节点的唯一标识 tId。
   private String id;
   //状态
   private Integer state;
   //pid
   private String pid;
   //义图标的 URL 路径。
   private String icon;
   //打开时的url
   private String iconClose;
   //自定义折叠时图标的 URL 路径
   private String iconOpen;
   //节点名称。
   private String name;
   // 节点的 展开 / 折叠 状态。 默认false
   private boolean open;
   // treeNode 节点是否为父节点。 true 是父节点  false 不是父节点
   private boolean isParent;
   
   /**  .*/
   private String title ;
   
   

  /*  *//** 名称 .*//*
    private String name ;
    
    *//** id .*//*
    private String id ;

    *//** 是否展开 .*//*
    private Boolean spread ;

    *//** 节点连接可选 .*//*
    private String href;
    *//** 子节点 .*//*
    private List<TreeNode> children;*/

}
