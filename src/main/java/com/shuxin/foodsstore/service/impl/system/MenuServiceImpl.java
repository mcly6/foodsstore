package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.Menu;
import com.shuxin.foodsstore.entity.system.RoleMenu;
import com.shuxin.foodsstore.repository.system.MenuRepository;
import com.shuxin.foodsstore.repository.system.RoleMenuRepository;
import com.shuxin.foodsstore.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;


    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> findAllByRoleId(Integer roleId) {

        //先差选中间表
        List<RoleMenu> roleMenuList = roleMenuRepository.findAllByRoleId(roleId);

        List<Integer> menuIdList = roleMenuList.stream().map(e -> e.getMenuId()).collect(Collectors.toList());

        //在返回List
        return menuRepository.findAllByIdIn(menuIdList);
    }
}
