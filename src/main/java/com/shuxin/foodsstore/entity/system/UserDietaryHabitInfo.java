package com.shuxin.foodsstore.entity.system;


import com.shuxin.foodsstore.commons.enums.BeliefEnum;
import com.shuxin.foodsstore.commons.enums.SelectEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: UserInfo
 * @Description: 用户饮食习惯信息表.
 * @Author liu yang
 * @Date 2018/1/17 14:29
 */
@Entity
@Data
public class UserDietaryHabitInfo implements Serializable {
    
    @Id
    private String id;
    
    /** 用户id .*/
    private Integer userId ;
    
    /** 身高 .*/
    private String height ;
    
    /** 体重 .*/
    private String weight ;
    
    /** 宗教信仰  0--默认无 1->佛教 2->伊斯兰教 9->其他.*/
    private Integer belief = BeliefEnum.DEFUAL.getCode();

    /** 食物过敏史 可多选","分隔.*/
    private String foodAllergy ;

    /** 食物过敏说明 .*/
    private String foodAllergyRemark ;

    /** 饮食禁忌  0--> 无 1-->有.*/
    private Integer  dietetic  = SelectEnum.NOT.getCode();

    /** 饮食禁忌说明 .*/
    private String dieteticRemark ;

    /** 创建时间 .*/
    private String createTime ;
    
    /** 修改时间 .*/
    private String updateTime ;
    
    
    
    

    
    
    
    


    
        
    
    
    
    
}
