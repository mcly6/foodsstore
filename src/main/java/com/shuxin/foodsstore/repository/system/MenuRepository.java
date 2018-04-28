package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* @ClassName: MenuRepository
* @Description: TODO(菜单操作.)
* @author liu yang  
* @date 2017/12/22 16:57  
*/ 
public interface MenuRepository extends JpaRepository<Menu,Integer> {
    /** 查询父ID下的左右菜单 .*/
    List<Menu> findByPid(Integer pid);

    List<Menu> findAllByIdIn(List<Integer> menuIdList);



}
