package com.shuxin.foodsstore.controller.product;


import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.service.ProductCategoryService;
import com.shuxin.foodsstore.service.ProductInfoService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
@Slf4j
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductInfoService infoService;

    @GetMapping("/list")
    public String toList() {

        return "product/category_list";
    }

    @RequestMapping("/categoryGrid")
    @ResponseBody
    public TableVO getCategoryGrid() {

        Sort sort = new Sort(Sort.Direction.ASC, "categorySerial");

        List<ProductCategory> categoryList = categoryService.findAll(sort);

        return TableVOResultUtils.success(categoryList);
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "product/category_add";
    }

    /**
     * @Title: add 添加或修改
     * @Param [productCategory, bindingResult]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/add")
    public ResultVO add(@Valid ProductCategory productCategory,
                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.CATEGORY_FORM_ERROR.getMessage());
        }

        //新分类
        categoryService.save(productCategory);


        log.info("添加商品类目:{}", productCategory.getCategoryName());

        return ResultVOUtils.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVO delete(@RequestParam("id") Integer id) {

        //根据类目查询类目下是否有商品信息
        List<ProductInfo> infoList = infoService.findAllByCategoryType(id);

        if (infoList == null || infoList.isEmpty()) {
            try {

                categoryService.delete(id);
                return ResultVOUtils.success();
            } catch (Exception e) {
                return ResultVOUtils.error(400, e.getMessage());
            }
        } else {

            return ResultVOUtils.error(400, ResultEnum.CATEGORY_PRODUCT_EXIST.getMessage());
        }


    }

    @GetMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable(value = "id") Integer id, Map<String, Object> map) {
        //查询选择的类目
        ProductCategory category = categoryService.findOne(id);
        return new ModelAndView("product/category_edit", (Map<String, ?>) map.put("category", category));

    }


    @RequestMapping("/getcategory")
    @ResponseBody
    public ProductCategory getCategory(@RequestParam("id") Integer id) {

        return categoryService.findOne(id);

    }

}
