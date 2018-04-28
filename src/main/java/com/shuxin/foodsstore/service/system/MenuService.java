package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();

    Menu save(Menu menu);

    List<Menu> findAllByRoleId(Integer roleId);
}
