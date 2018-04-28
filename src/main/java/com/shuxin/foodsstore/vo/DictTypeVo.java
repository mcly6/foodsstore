package com.shuxin.foodsstore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DictTypeVo {


    @JsonProperty("typegroupid")
    private DictTypeGroup dictTypeGroup;//类型分组

    @JsonProperty("typepid")
    private com.shuxin.foodsstore.entity.system.DictType dictType;//父类型

    @JsonProperty("typename")
    private String typename;//类型名称

    @JsonProperty("typecode")
    private String typecode;//类型编码

    @JsonProperty("createTime")
    private Date createTime;//创建时间

    @JsonProperty("updateTime")
    private Date updateTime;//修改时间

    @JsonProperty("dictTypes")
    private List<com.shuxin.foodsstore.entity.system.DictType> dictTypes = new ArrayList();

}
