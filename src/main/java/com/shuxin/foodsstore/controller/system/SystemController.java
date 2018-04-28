package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import com.shuxin.foodsstore.service.system.SystemService;
import com.shuxin.foodsstore.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: SystemController
 * @Description: 系统操作.
 * @Author liu yang
 * @Date 2018/1/3 10:39
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController {

    @Autowired
    private SystemService systemService;

    //字典操作
    @RequestMapping("/dictTypeList")
    public ModelAndView dictTypeList(HttpServletRequest request) {

        return new ModelAndView("system/dict_type");
    }


    /**
     * @Title: getList 分页查询所有字典数据
     * @Param [curr, limit] 当前页数,每页显示的条数
     * @Return com.shuxin.foodsstore.vo.PageVO
     */
    @RequestMapping("/dictTypeGrid")
    @ResponseBody
    public TableVO getList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer limit) {

        Page<DictTypeGroup> dictTypeGroups = systemService.findPage(page-1, limit);
        return TableVOResultUtils.success(dictTypeGroups);

    }

}
