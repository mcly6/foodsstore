package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.UserDietaryHabitInfo;
import com.shuxin.foodsstore.repository.system.UserDietaryHabitInfoRepository;
import com.shuxin.foodsstore.service.system.UserDietaryHabitInfoService;
import com.shuxin.foodsstore.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserDietaryHabitInfoServiceImpl implements UserDietaryHabitInfoService {

    @Autowired
    private UserDietaryHabitInfoRepository infoRepository;

    @Autowired
    private UserService userService;


    @Override
    public UserDietaryHabitInfo save(UserDietaryHabitInfo info) {
        return infoRepository.save(info);
    }
}
