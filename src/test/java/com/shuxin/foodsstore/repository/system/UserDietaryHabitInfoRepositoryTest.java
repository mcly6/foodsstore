package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.entity.system.UserDietaryHabitInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDietaryHabitInfoRepositoryTest {

    @Autowired
    private UserDietaryHabitInfoRepository infoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        UserDietaryHabitInfo info = new UserDietaryHabitInfo();
        User user = userRepository.findByUsername("15863157845");

        info.setId(IDUtils.getUUID());
        info.setUserId(user.getId());

        info.setDieteticRemark("没有任何饮食禁忌");
        info.setFoodAllergy("1,2,3,4");
        info.setFoodAllergyRemark("不能太咸,不能太辣,不能太酸,不能太苦,不能太甜!!!");
        info.setHeight("175");
        info.setWeight("200");

        UserDietaryHabitInfo result = infoRepository.save(info);

        Assert.assertNotNull(result);

    }

}