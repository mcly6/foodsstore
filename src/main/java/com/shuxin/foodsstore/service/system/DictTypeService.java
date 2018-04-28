package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DictTypeService {

    DictType findOne(String id);


    Page<DictType> findAll(PageRequest request);

    /**   
     * @Title: 分页按照条件查询
     * @Param
     * @Return
     */ 
    Page<DictType> findAllByDictType(String dictType, PageRequest request);

    Page<DictType> findAllByDictTypeAndDictName(String dictType, String dictName, PageRequest request);

    Page<DictType> getListPage(String dictType, String dictName, PageRequest request);

    DictType save(DictType dictType);

    void delete(String id);
}
