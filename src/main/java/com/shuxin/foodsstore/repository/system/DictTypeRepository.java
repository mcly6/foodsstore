package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DictTypeRepository  extends JpaRepository<DictType, String>,JpaSpecificationExecutor<DictType> {

    Page<DictType> findAllByDictType(String dictType, Pageable pageable);

    Page<DictType> findAllByDictTypeAndDictName(String dictType, String dictName, Pageable pageable);




}
