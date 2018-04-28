package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    RoleRepository repository;

    @Test
    public void create() {

        Role role = new Role();
        role.setId(3);
        role.setName("管理员");
        role.setWhetherDefault(0);
        role.setDescription("这个是每个医院订餐系统的管理员角色");
        role.setWxMark(" eyehospitalwx");

        Role role1 = repository.save(role);

        Assert.assertNotNull(role1.toString());

    }
}