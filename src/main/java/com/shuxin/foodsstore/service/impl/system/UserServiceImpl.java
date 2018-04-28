package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.enums.UserTypeEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.repository.system.UserRepository;
import com.shuxin.foodsstore.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;


/**
 * @author liu yang
 * @ClassName: UserServiceImpl
 * @Description: TODO(用户服务层.)
 * @date 2017/12/25 11:38
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;




    @Override
    /**
     * @Title: getUser 根据usern|password|type查询用户
     * @param [username, password, type]
     * @return com.shuxin.foodsstore.entity.system.User
     */
    public User getUser(String username, String password, Integer type) {
        return userRepository.findUserByUsernameAndPasswordAndType(username, password, type);
    }

    @Override
    /**
     * @Title: register 用户注册
     * @Param [username, password, type]  
     * @Return com.shuxin.foodsstore.entity.system.User
     */
    public User register(String username, String password, Integer type) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setType(UserTypeEnum.PATIENT.getCode());
        //TODO("OPENID")

       User info = userRepository.save(user);

        //User info  = userRepository.saveAndFlush(user);

        if (info == null) {
            log.error("[用户注册失败] username={}, password={}", username, password);

            throw new MyRuntimeException(ResultEnum.USER_REGISTER_ERROR);
        }

        return info;
    }

    @Override
    public boolean findByUserName(String username) {

        //User resultUser = userRepository.findByUsername(username);
        List<User> resultUserList=userRepository.findAllByUsername(username);

        if (resultUserList != null && resultUserList.size()> 0) {
            return true;
        }else {
            return false;
        }



    }
    
    @Override
    /**   
     * @Title: findByUsername 根据用户名(手机号)查询用户信息
     * @Param [username]  
     * @Return com.shuxin.foodsstore.entity.system.User  
     */ 
    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }


    public Page<User> findByUserType(Integer userType , Integer page , Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);

        return userRepository.findByType(userType, pageRequest);
    }

    @Override
    public User findByNameAndPassword(User user) {

       User userResult =  userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (user != null) {
            return  userResult;
        }
        return  null;

    }

    @Override
    public User sava(User user) {

        //return userRepository.save(user);
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Page<User> findAllByType(Integer userType, PageRequest pageRequest) {


         return userRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {


                Path<Integer> typePath = root.get("userType");

                query.where(cb.equal(typePath, userType));
                return null;
            }
        }, pageRequest);


    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
