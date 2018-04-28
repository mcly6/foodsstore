package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.UserDietaryHabitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDietaryHabitInfoRepository extends JpaRepository<UserDietaryHabitInfo,String> {

    UserDietaryHabitInfo findByUserId(String userId);



}
