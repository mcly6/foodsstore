package com.shuxin.foodsstore.vo;

import lombok.Data;

/**
 * @ClassName: UserVO
 * @Description: 用户页面显示信息.
 * @Author liu yang
 * @Date 2018/1/18 15:36
 */
@Data
public class UserVO {

    private Integer id;
    /**
     * 用户名称.
     */
    private String username;
    /**
     * 用户类型.
     */
    private Integer type;
    /**
     * 住院号ID.
     */
    private String hosNum;
    /**
     * 昵称.
     */
    private String nickname;



}
