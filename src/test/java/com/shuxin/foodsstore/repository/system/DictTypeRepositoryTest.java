package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.entity.system.DictType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictTypeRepositoryTest {

    @Autowired
    private DictTypeRepository repository;
    @Autowired
    private DictTypeGroupRepository groupRepository;

    @Test
    public void saveTest() {
        DictType type = new DictType();
        type.setId(IDUtils.getUUID());




        repository.save(type);


    }



}