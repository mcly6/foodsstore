package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.RoleUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleUserRepositoryTest {
    @Autowired
    private  RoleUserRepository repository;

    @Test
    public void getTest() {

        List<RoleUser> roleUserList = repository.findAllByUserId(7);

        for (RoleUser roleUser : roleUserList) {
            System.out.println(roleUser.toString());

        }
    }

}