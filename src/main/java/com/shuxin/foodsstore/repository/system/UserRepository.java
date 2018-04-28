package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    User findUserByUsernameAndPasswordAndType(String username, String password , Integer type);

    User findUserByUsernameAndPassword(String username, String password);

    User findByOpenid(String openid);

    User findByUsername(String username);

    List<User> findAllByUsername(String username);

    Page<User> findByType(Integer userType, Pageable pageable);


}
