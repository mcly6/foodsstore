package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import com.shuxin.foodsstore.repository.system.DictTypeGroupRepository;
import com.shuxin.foodsstore.repository.system.DictTypeRepository;
import com.shuxin.foodsstore.service.system.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Autowired
    private DictTypeRepository typeRepository;

    @Autowired
    private DictTypeGroupRepository typeGroupRepository;


    @Override
    public List<DictTypeGroup> findAll() {

        List<DictTypeGroup> typeGroupList = typeGroupRepository.findAll();
        return typeGroupList;
    }


    @Override
    public DictTypeGroup findOneByID(String typeGroupId) {

        return typeGroupRepository.findOne(typeGroupId);
    }

    @Override
    public Page<DictTypeGroup> findPage(Integer page,Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return typeGroupRepository.findAll(pageRequest);
    }
}
