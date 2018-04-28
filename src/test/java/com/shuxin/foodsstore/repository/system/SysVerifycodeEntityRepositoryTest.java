package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.entity.system.SysVerifyCodeEntity;
import com.shuxin.foodsstore.service.system.VerifyCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysVerifycodeEntityRepositoryTest {

    @Autowired
    private SysVerifycodeEntityRepository codeRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private VerifyCodeService codeService;


    @Test
    public void saveTest() {
        SysVerifyCodeEntity syscode = new SysVerifyCodeEntity();

        syscode.setCode("445566");
        syscode.setCreateTime(new Date());
        syscode.setEffectMinute(5);
        syscode.setEndTime(new Date());
        syscode.setIsuse(0);
        syscode.setType(11);
        syscode.setPhone("15863157845");

        codeRepository.save(syscode);
    }

    @Test
    public void checkVerifyCode() throws Exception {

        SysVerifyCodeEntity syscode = codeRepository.findOne("4028b881612216b701612216c1420000");


        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        Example<SysVerifyCodeEntity> ex = Example.of(syscode, matcher);

        SysVerifyCodeEntity one = codeRepository.findOne(ex);

        System.out.println(one.toString());
    }

    @Test
    public void counteTest() throws  Exception{


        List resultList = em.createQuery("select count(1) from  SysVerifycodeEntity c where c.phone=:phone and c.type=:type")
                .setParameter("phone", "15863157845")
                .setParameter("type", "22")
                .getResultList();


        System.out.println(resultList.get(0));


    }

    @Test
    public void phoneTest() throws  Exception {

        List resultList = em.createQuery("SELECT" +
                " COUNT(c)" +
                "FROM SysVerifycodeEntity c" +
                " WHERE c.phone= :phone" +
                " AND c.type= :type" +
                " AND c.code= :code" +
                " AND c.isuse=0" +
                "AND c.endTime > :nowData ").setParameter("phone", "15863157845").setParameter("type", "11")
                .setParameter("code", "112233").setParameter("nowData",new Date()).getResultList();


        System.out.println(resultList.get(0));

    }

    @Test
    public void checkPhoeTest() {
        SysVerifyCodeEntity syscode = new SysVerifyCodeEntity();

        syscode.setCode("898989");
        syscode.setCreateTime(new Date());
        syscode.setEffectMinute(5);
        syscode.setEndTime(new Date());
        syscode.setIsuse(0);
        syscode.setType(11);
        syscode.setPhone("15863157845");

        codeService.save(syscode);



    }

}