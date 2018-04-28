package com.shuxin.foodsstore.service.impl.system;


import com.shuxin.foodsstore.entity.system.Menu;
import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.service.system.MenuService;
import com.shuxin.foodsstore.service.system.PermissionService;
import com.shuxin.foodsstore.service.system.RoleService;
import com.shuxin.foodsstore.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: PermissionServiceImpl
 * @Description: 权限服务类.
 * @Author liu yang  
 * @Date 2018/2/1 15:23  
 */ 
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    @Override
    /**   
     * @Title: findByUsername 根据用户名查询用户对象
     * @Param [username]  
     * @Return com.shuxin.foodsstore.entity.system.User  
     */ 
    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    /**   
     * @Title: getRolesName 根据用户id查询所有的角色名称
     * @Param [id]  用户id
     * @Return java.util.Set<java.lang.String>  
     */ 
    public Set<String> getRolesName(Integer userId) {

       List<Role> roleList =  roleService.findAllByUserId(userId);

        return roleList.stream().map(e -> e.getName()).collect(Collectors.toSet());

    }

    @Override
    /**   
     * @Title: getRoleList 根据用户ID查询所有角色
     * @Param [id]  
     * @Return java.util.List<com.shuxin.foodsstore.entity.system.Role>  
     */ 
    public List<Role> getRoleList(Integer userId) {

       return roleService.findAllByUserId(userId);
    }

    @Override
    /**   
     * @Title: getPermissionsName 根据角色ID查询所有的资源名称
     * @Param [id]  
     * @Return java.util.Collection<java.lang.String>  
     */ 
    public Collection<String> getMenuName(Integer roleId) {

        List<Menu> menuList = menuService.findAllByRoleId(roleId);

        return menuList.stream().map(e->e.getName()).collect(Collectors.toList());
    }
}
