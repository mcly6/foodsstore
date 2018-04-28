package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.entity.system.RoleUser;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.repository.system.RoleRepository;
import com.shuxin.foodsstore.repository.system.RoleUserRepository;
import com.shuxin.foodsstore.service.system.RoleService;
import com.shuxin.foodsstore.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @param
 * @Title: 角色服务层
 * @return
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    public RoleVO createRoel(RoleVO roleVO) {
        return null;
    }

    /**
     * 获取当前用户下的所有角色记录 .
     */
    @Override
    public List<Role> getRolesByUser(User user) {

        List<Role> list = new ArrayList<Role>();
        Map<String, Object> map = new HashMap<String, Object>();

        return findAllByUserId(user.getId());
    }
    
    
    @Override
    /**   
     * @Title: findAllByUserId 根据用户ID查询所有用户角色
     * @Param [userId]  
     * @Return java.util.List<com.shuxin.foodsstore.entity.system.Role>  
     */ 
    public List<Role> findAllByUserId(Integer userId) {

        //先差选role list<id>
        List<RoleUser> roleUserList = roleUserRepository.findAllByUserId(userId);


        List<Integer> roleIdList = roleUserList.stream().map(e -> e.getRoleId()).collect(Collectors.toList());

        //在查询List<role>
        return roleRepository.findAllByIdIn(roleIdList);


    }

    @Override
    public Page<Role> findAll(int page, Integer size) {
        return roleRepository.findAll(new PageRequest(page,size));
    }
}
