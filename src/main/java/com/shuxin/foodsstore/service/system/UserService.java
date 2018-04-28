package com.shuxin.foodsstore.service.system;


import com.shuxin.foodsstore.entity.system.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {

    User getUser(String username, String password , Integer type);

    //1. 注册

    User register(String username, String password, Integer type);

    boolean findByUserName(String username);

    User findByUsername(String username);

    Page<User> findByUserType(Integer userType , Integer page, Integer sieze);

    User findByNameAndPassword(User user);


    User sava(User userInfo);

    Page<User> findAll(PageRequest pageRequest);

    Page<User> findAllByType(Integer userType, PageRequest pageRequest);

    User findOne(Integer id);

    User save(User user);
}
