package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu,Integer>{

    List<RoleMenu> findAllByRoleId(Integer roleId);
}
