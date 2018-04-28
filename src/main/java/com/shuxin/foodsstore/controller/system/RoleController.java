package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.system.Role;
import com.shuxin.foodsstore.service.system.RoleService;
import com.shuxin.foodsstore.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toRoleList")
    public ModelAndView toRoleList() {

        return new ModelAndView("/system/role_list");
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableVO getList(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "limit") Integer limit) {

        Page<Role> rolePage = roleService.findAll(page - 1, limit);
        return TableVOResultUtils.success(rolePage);

    }

}
