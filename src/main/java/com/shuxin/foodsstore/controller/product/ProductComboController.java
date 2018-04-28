package com.shuxin.foodsstore.controller.product;


import com.shuxin.foodsstore.commons.enums.CategoryModelEnum;
import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.entity.ProductCombo;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.service.ProductCategoryService;
import com.shuxin.foodsstore.service.ProductComboService;
import com.shuxin.foodsstore.service.ProductInfoService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ProductComboController
 * @Description: 商品套餐.
 * @Author liu yang
 * @Date 2018/1/12 10:44
 */
@RequestMapping("/combo")
@Controller
@Slf4j
public class ProductComboController {

    @Autowired
    private ProductComboService comboService;

    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductCategoryService categoryService ;


    @RequestMapping("/list")
    public String list() {
        return "product/combo_list";
    }

    @RequestMapping("/comboGrid")
    @ResponseBody
    public TableVO comboGrid(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "limit", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);

        Page<ProductCombo> comboPage = comboService.findAll(request);

        return TableVOResultUtils.success(comboPage);
    }

    /**  跳转到添加页面.*/
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Map<String, Object> map) {

        //所有菜品的分类
        List<ProductCategory> cuisineCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.CUISINE.getCode());

        List<Integer> categoryTypeList = cuisineCategoryList.stream()
                .map(e -> e.getCategoryId())
                .collect(Collectors.toList());

        //查询所有上架菜品信息 --所有上架
        //List<ProductInfo> infoList = infoService.findUpAll();
        List<ProductInfo> infoList = infoService.findAllByProductStatusAndCategoryTypeIn(ProductStatusEnum.UP.getCode(), categoryTypeList);

        //查询所有套餐类目
        List<ProductCategory> comboCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.COMBO.getCode());

        ModelAndView modelAndView = new ModelAndView("product/combo_add");

        modelAndView.addObject("infoList", infoList);
        modelAndView.addObject("comboCategoryList", comboCategoryList);

        return modelAndView;
    }


    @RequestMapping("/add")
    @ResponseBody
    public ResultVO add(@Valid ProductInfo info, BindingResult bindingResult) {
        info.setIfCombo(1);

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.PRODUCT_COMBO_FORM_ERRO.getMessage());
        }

        if (StringUtils.isEmpty(info.getProductId())) { //新建

            info.setProductId(IDUtils.genUniqueKey());

        }

        try {

           // ProductCombo resultCombo = comboService.save(combo);
            ProductInfo saveResult = infoService.save(info);

        } catch (Exception e) {

            return ResultVOUtils.error(400, ResultEnum.SAVE_FAIL.getMessage());
        }

        return ResultVOUtils.success();
    }


    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable("id") String id, Map<String, Object> map) {


        ProductInfo combo = infoService.findOne(id);
        //所有菜品的分类
        List<ProductCategory> cuisineCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.CUISINE.getCode());

        List<Integer> categoryTypeList = cuisineCategoryList.stream()
                .map(e -> e.getCategoryId())
                .collect(Collectors.toList());

        //查询所有上架菜品信息 --所有上架
        //List<ProductInfo> infoList = infoService.findUpAll();
        List<ProductInfo> infoList = infoService.findAllByProductStatusAndCategoryTypeIn(ProductStatusEnum.UP.getCode(), categoryTypeList);

        //查询所有套餐类目
        List<ProductCategory> comboCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.COMBO.getCode());

        ModelAndView modelAndView = new ModelAndView("product/combo_edit");
        modelAndView.addObject("infoList", infoList);
        modelAndView.addObject("combo", combo);
        modelAndView.addObject("comboCategoryList", comboCategoryList);

        return modelAndView;


    }

    @RequestMapping("/delete")
    public ResultVO delete(@RequestParam("id") String id) {
        ProductCombo combo = comboService.findOne(id);

        if (combo != null) {

            try {

                comboService.delete(id);
            } catch (Exception e) {
                return ResultVOUtils.error(400, e.getMessage());

            }

        } else {
            return ResultVOUtils.error(400, ResultEnum.PRODUCT_COMBO_NOT_EXIST.getMessage());

        }
        return ResultVOUtils.success();
    }


}
