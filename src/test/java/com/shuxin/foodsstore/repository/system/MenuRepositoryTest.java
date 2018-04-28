package com.shuxin.foodsstore.repository.system;

import com.shuxin.foodsstore.commons.enums.MenuTypeEnum;
import com.shuxin.foodsstore.entity.system.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MenuRepositoryTest {
    @Autowired
    MenuRepository repository;

    @Test
    public void  create(){
        Menu menu = new Menu();

        menu.setCode("orderfood_1");
        menu.setIcon("http://abad.sfsfs.123.pang_1");
        menu.setRemark("显示设置菜单_1");
        menu.setName("设置_1");
        menu.setSeq(0);
        menu.setUri("http://abc.efg.higk/action_1");
        menu.setType(MenuTypeEnum.ACTION.getCode());
        menu.setPid(0);



        Menu menu1 = repository.save(menu);
        Assert.assertNotNull(menu1);
    }
    @Test
    public void findByPid() throws Exception {
        List<Menu> menus = repository.findByPid(0);
        log.info("menus 长度: {}",menus.size());
        for (Menu menu :
                menus) {
           log.info("name:{}",menu.getName());
        }
    }

}