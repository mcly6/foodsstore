package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.SysVerifyCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysVerifycodeEntityRepository extends JpaRepository<SysVerifyCodeEntity,String> {

    SysVerifyCodeEntity findByPhoneAndType(String phone, Integer type);

    SysVerifyCodeEntity findByPhone(String phone);


    /*    @Query("SELECT COUNT(1) FROM sys_verifycode WHERE c.phone=:phone AND c.type=:type AND c.code=:code AND c.isuse=0 AND c.end_time>NOW()")
    int checkVerifyCode(String phone, String code, String type, Integer effectMinute);*/
}
