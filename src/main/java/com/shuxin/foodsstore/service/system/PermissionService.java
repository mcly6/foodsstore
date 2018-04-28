package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PermissionService {

    User findByUsername(String username);

    Set<String> getRolesName(Integer userId);

    List<Role> getRoleList(Integer userId);

    Collection<String> getMenuName(Integer roleId);
}
