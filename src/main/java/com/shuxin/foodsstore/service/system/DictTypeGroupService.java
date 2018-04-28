package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictTypeGroupService {
    List<DictTypeGroup> getDictTypeGroupList();

    DictTypeGroup save(DictTypeGroup typeGroup);

    DictTypeGroup findOne(String id);

    void delete(String id);

    Page<DictTypeGroup> findAll(Pageable pageable);
}
