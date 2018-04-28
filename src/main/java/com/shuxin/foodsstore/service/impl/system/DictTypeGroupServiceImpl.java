package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import com.shuxin.foodsstore.repository.system.DictTypeGroupRepository;
import com.shuxin.foodsstore.service.system.DictTypeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class DictTypeGroupServiceImpl implements DictTypeGroupService {

    @Autowired
    private DictTypeGroupRepository groupRepository;

    public List<DictTypeGroup> getDictTypeGroupList() {
        return groupRepository.findAll();
    }

    @Override
    public DictTypeGroup save(DictTypeGroup typeGroup) {
        return groupRepository.save(typeGroup);
    }

    @Override
    public DictTypeGroup findOne(String id) {
        return groupRepository.findOne(id);
    }

    /** 添加/编辑 .*/
    public DictTypeGroup add(@Valid DictTypeGroup group) {

        return  groupRepository.save(group);
    }
    /** 删除 .*/
    public void delete(String id) {

        groupRepository.delete(id);
    }

    @Override
    public Page<DictTypeGroup> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    /** 查询所有字典分类 .*/
    public List<DictTypeGroup> getList() {
        return groupRepository.findAll();
    }


}
