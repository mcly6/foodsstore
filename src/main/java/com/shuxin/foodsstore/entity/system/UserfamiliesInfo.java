package com.shuxin.foodsstore.entity.system;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: UserfamiliesInfo
 * @Description: 病人用户家属.
 * @Author liu yang
 * @Date 2018/1/19 9:25
 */
@Entity
@Data
public class UserfamiliesInfo implements Serializable {

    /**
     * id .
     */
    @Id
    private String id;

    /**
     * 姓名 .
     */
    private String familyName;

    /**
     * 性别 .
     */
    private String familyGender;

    /**
     * 年龄 .
     */
    private String familyAge;

    /**
     * 身高 .
     */
    private String familyHeight;

    /**
     * 体重 .
     */
    private String familyWeight;


}
