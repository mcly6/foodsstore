package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUserRepository extends JpaRepository<RoleUser ,Integer> {

    List<RoleUser> findAllByUserId(Integer userId);

}
