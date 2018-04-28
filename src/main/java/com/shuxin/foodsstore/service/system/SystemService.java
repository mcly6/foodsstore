package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SystemService {

    List<DictTypeGroup>  findAll();

    DictTypeGroup findOneByID(String typeGroupId);

    Page<DictTypeGroup> findPage(Integer page, Integer size);


}
