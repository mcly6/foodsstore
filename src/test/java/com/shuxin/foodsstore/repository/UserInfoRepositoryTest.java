package com.shuxin.foodsstore.repository;

import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.repository.system.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {

    @Autowired
    UserRepository userInfoRepository;

    @Test
    public void save() {

        User user = new User();
        user.setOpenid("abc");
        user.setPassword("123456789");
        user.setUsername("1334343344343");
        user.setType(1);

        userInfoRepository.save(user);
    }

    @Test
    public void findByOpenId() {
        User userInfo = userInfoRepository.findByOpenid("abc");
        System.out.println(userInfo.toString());
    }


}