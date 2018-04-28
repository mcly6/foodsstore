package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.ServletUtil;
import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.system.Menu;
import com.shuxin.foodsstore.model.MenuNode;
import com.shuxin.foodsstore.service.system.MenuService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/list")
    public String getList() {
        log.info("访问到list接口");


        return "system/menu_list";
    }

    @GetMapping("/toAdd")
    public String toAdd() {


        return "system/menu_add";
    }
    @RequestMapping("/menuGrid")
    @ResponseBody
    public TableVO getMenus() {

        List<Menu> menus = menuService.findAll();


        return TableVOResultUtils.success(menus);

    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultVO addMenu(@Valid Menu menu,
                            BindingResult bindingResult) {

         Menu result =   menuService.save(menu);

        return ResultVOUtils.success();

    }


    //根据登录用户的角色权限确定menujson

    @RequestMapping("/getRoleMenuList")
    @ResponseBody
    public List<MenuNode> getRoleMenuList() {

        Integer id = ServletUtil.getCurrentUser().getId();

        System.out.println("用户ID"+id);

        return null;
    }




}
