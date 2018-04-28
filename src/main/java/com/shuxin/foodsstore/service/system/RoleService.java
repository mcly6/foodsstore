package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.vo.RoleVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    RoleVO createRoel(RoleVO roleVO);

    List<Role> getRolesByUser(User user);

    List<Role> findAllByUserId(Integer userId);

    Page<Role> findAll(int i, Integer limit);
}
