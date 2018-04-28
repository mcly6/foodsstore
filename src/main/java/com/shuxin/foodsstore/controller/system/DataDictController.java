package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.system.DictType;
import com.shuxin.foodsstore.entity.system.DictTypeGroup;
import com.shuxin.foodsstore.model.TreeNode;
import com.shuxin.foodsstore.service.system.DictTypeGroupService;
import com.shuxin.foodsstore.service.system.DictTypeService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DataDictController
 * @Description: 字典数据控制层.
 * @Author liu yang
 * @Date 2018/1/19 11:11
 */
@Controller
@RequestMapping("/dict")
public class DataDictController {

    @Autowired
    private DictTypeGroupService groupService;

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * @Title: 列表数据
     * @Param
     * @Return
     */
    @GetMapping("/toList")
    public ModelAndView toList() {
        ModelAndView modelAndView = new ModelAndView("system/dictionary_list");
        return modelAndView;
    }

    //字典类别树形菜单
    @RequestMapping("/getDictTypeGroupList")
    @ResponseBody
    public ResultVO getDictTypeGroupList(HttpServletRequest request) {

        List<DictTypeGroup> dictTypeGroupList = groupService.getDictTypeGroupList();

        List<TreeNode> nodeList = new ArrayList<>();

        for (DictTypeGroup group : dictTypeGroupList) {
            TreeNode node = new TreeNode();

            node.setId(group.getId());
            node.setName(group.getTypegroupname());
            node.setParent(false);
            node.setOpen(false);
            node.setIcon("../static/js/zTree/css/zTreeStyle/img/diy/3.png");

            node.setTitle(group.getTypegroupcode());

            //node.setIcon("");

           /*
            node.setId(group.getId()+"");
            node.setName(group.getTypegroupname());
            node.setSpread(false);
            node.setHref(group.getTypegroupcode());
            */

            nodeList.add(node);

        }

        return ResultVOUtils.success(nodeList);

    }

    /**
     * @Title: 字典表格分页信息
     * @Param
     * @Return
     */
    @RequestMapping("/dictGrid")
    @ResponseBody
    public TableVO getDictGrid(@RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "limit", defaultValue = "10") Integer size,
                               @RequestParam(name = "dictType") String dictType,
                               @RequestParam(name = "dictName") String dictName) {

        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<DictType> listPage;

        if (StringUtils.isEmpty(dictType) && StringUtils.isEmpty(dictName)) {
            listPage = dictTypeService.findAll(pageRequest);
        } else {

            listPage = dictTypeService.getListPage(dictType, dictName, pageRequest);
        }

        return TableVOResultUtils.success(listPage);
    }

    /**
     * @Title: 添加字典类型
     * @Param
     * @Return
     */
    @RequestMapping("/toAddType")
    public ModelAndView toAddType() {

        return new ModelAndView("system/dictTypeGroup_add");
    }

    /**
     * @Title: 添加字典类型
     * @Param
     * @Return
     */
    @RequestMapping("/addType")
    @ResponseBody
    public ResultVO addType(@Valid DictTypeGroup typeGroup, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_FORM_INFO_ERROR.getMessage());
        }

        if (typeGroup.getId() == null) {
            typeGroup.setId(IDUtils.getUUID());
        }

        DictTypeGroup save = groupService.save(typeGroup);

        if (save != null) {
            return ResultVOUtils.success();

        } else {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_SAVE_FAIL.getMessage());
        }


    }

    /**
     * @Title: 编辑字典分类
     * @Param
     * @Return
     */
    @RequestMapping("/toEditType/{id}")
    public ModelAndView toEidtType(@PathVariable("id") String id, Map<String, Object> map) {

        if (!StringUtils.isEmpty(id)) {

            DictTypeGroup typeGroup = groupService.findOne(id);
            return new ModelAndView("system/dictTypeGroup_edit", (Map<String, ?>) map.put("typeGroup", typeGroup));

        }

        return new ModelAndView("common/erro");

    }

    /**
     * @Title: 删除字典分类
     * @Param
     * @Return
     */
    @RequestMapping("/deleteDictTypeGroup/{id}")
    @ResponseBody
    public ResultVO deleteDictTypeGroup(@PathVariable("id") String id) {

        try {
            groupService.delete(id);
            return ResultVOUtils.success(ResultEnum.DICTTYPE_DELETE_SUCCESS.getMessage());
        } catch (Exception e) {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_DELETE_FAIL.getMessage());
        }
    }


    @RequestMapping("/toAddDictType")
    public ModelAndView toAddDictType(Map<String, Object> map) {
        //查询所有类别
        List<DictTypeGroup> dictTypeGroupList = groupService.getDictTypeGroupList();

        return new ModelAndView("system/dictType", (Map<String, ?>) map.put("groupList", dictTypeGroupList));

    }

    /**
     * @Title: 添加/编辑字典
     * @Param
     * @Return TODO(时间问题)
     */
    @RequestMapping("/addDictType")
    @ResponseBody
    public ResultVO addDictType(@Valid DictType dictType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_FORM_ERROR.getMessage());
        }
        DictType one = new DictType();

        if (!StringUtils.isEmpty(dictType.getId())) {//新建

            one = dictTypeService.findOne(dictType.getId());

        } else {
            dictType.setId(IDUtils.getUUID());
        }

        BeanUtils.copyProperties(dictType, one);

        DictType type = dictTypeService.save(one);
        if (type == null) {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_SAVE_FAIL.getMessage());
        }

        return ResultVOUtils.success();
    }

    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEidt(@PathVariable("id") String id) {

        ModelAndView view = new ModelAndView("system/dictType");

        //查询所有类别
        List<DictTypeGroup> dictTypeGroupList = groupService.getDictTypeGroupList();

        DictType dictType = dictTypeService.findOne(id);

        view.addObject("groupList", dictTypeGroupList);
        view.addObject("dict", dictType);

        return view;
    }

    @RequestMapping("/delete")
    public ResultVO delete(@RequestParam("id") String id) {

        if (StringUtils.isEmpty(id)) {

            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_INFO_ERROR.getMessage());
        }
        try {

            dictTypeService.delete(id);
            return ResultVOUtils.success();
        } catch (Exception e) {
            return ResultVOUtils.error(400, ResultEnum.DICTTYPE_DELETE_FAIL.getMessage());
        }

    }

    /**
     * @Title: 通过id查询字典名称
     * @Param
     * @Return
     */
    @RequestMapping("/getTypeGroupName")
    @ResponseBody
    public DictTypeGroup getTypeGroupName(@RequestParam("id") String id) {

        DictTypeGroup typeGroup = groupService.findOne(id);
        return typeGroup;


    }

}
