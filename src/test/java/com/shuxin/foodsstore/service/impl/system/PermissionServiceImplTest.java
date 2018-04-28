package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.service.system.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

    @Autowired
    private PermissionService service;

    @Test
    public void findByUsername() throws Exception {
        User user = service.findByUsername("15863157845");

        System.out.println(user.toString());
    }

    @Test
    public void getRolesName() throws Exception {

        Set<String> rolesName = service.getRolesName(7);

        for (String s : rolesName) {
            System.out.println(s);
            System.out.println("----");

        }
        
        
    }

    @Test
    public void getRoleList() throws Exception {

        List<Role> roleList = service.getRoleList(7);

        for (Role role : roleList) {
            System.out.println(role.toString());

        }
    }

    @Test
    public void getMenuName() throws Exception {
        Collection<String> menuName = service.getMenuName(1);
        for (String s : menuName) {
            System.out.println(s);

        }



    }

}