package com.shuxin.foodsstore.entity.system;

import com.shuxin.foodsstore.commons.base.IdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DictTypeGroup
 * @Description: 字典分组信息.
 * @Author liu yang  
 * @Date 2018/1/19 11:18
 */ 
@Data
@Entity
//@Table(name = "dict_type_group")
public class DictTypeGroup extends IdEntity implements Serializable {

    @Column(name = "typegroupname", length = 50)
    private String typegroupname;
    @Column(name = "typegroupcode", length = 50)
    private String typegroupcode;

    private Date createTime;//创建时间
    private Date updateTime;//修改时间

}
