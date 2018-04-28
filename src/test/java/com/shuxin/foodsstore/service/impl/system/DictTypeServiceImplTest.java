package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.DictType;
import com.shuxin.foodsstore.service.system.DictTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictTypeServiceImplTest {

    @Autowired
    private DictTypeService service;

    @Test
    public void getListPage() throws Exception {


        Page<DictType> listPage = service.getListPage("4028b88160ba0c320160ba0c3b2c0000"
                                                                , "一级"
                                                                , new PageRequest(0, 10));

        System.out.println(listPage.getContent().toString());
    }

}