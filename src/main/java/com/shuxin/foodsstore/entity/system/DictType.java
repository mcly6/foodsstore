package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.base.IdEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: DictType
 * @Description: 字典详细信息.   
 * @Author liu yang  
 * @Date 2018/1/19 11:19
 */ 
@Entity
@Data
public class DictType extends IdEntity implements Serializable {

    /**
     * 字典类别 对应DictTypeTable中的typecode
     */
    private String dictType;
    /**
     * 字典代码
     */
    private String dictCode;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 备注信息
     */
    private String remark;

    private  Date createTime;
    private Date updateTime;

}
