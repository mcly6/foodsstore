package com.shuxin.foodsstore.controller.product;


import com.shuxin.foodsstore.commons.enums.CategoryModelEnum;
import com.shuxin.foodsstore.commons.enums.ProductStatusEnum;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.IDUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.commons.utils.TableVOResultUtils;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.formdata.ProductInfoForm;
import com.shuxin.foodsstore.service.ProductCategoryService;
import com.shuxin.foodsstore.service.ProductInfoService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ProductInfoController
 * @Description: 商品信息控制.
 * @Author liu yang
 * @Date 2018/1/12 11:08
 */
@Controller
@RequestMapping("/productInfo")
@Slf4j
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * @Title: 跳转到商品类目列表
     * @Param
     * @Return
     */
    @GetMapping("/list")
    public ModelAndView toList() {

        ModelAndView modelAndView = new ModelAndView("product/product_list");

        return modelAndView;
    }


    /**
     * @Title: getProductGrid 商品类目列表
     * @Param [page, size]
     * @Return com.shuxin.foodsstore.vo.PageVO
     */
    @RequestMapping("/productGrid")
    @ResponseBody
    public TableVO getProductGrid(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "limit", defaultValue = "10") Integer size) {

        PageRequest pageRequest = new PageRequest(page - 1, size);

        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);

        return TableVOResultUtils.success(productInfoPage);

    }

    /**
     * @Title: 跳转到添加页面
     * @Param
     * @Return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Map<String, ?> map) {

        //所有菜品的分类
        List<ProductCategory> cuisineCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.CUISINE.getCode());


        ModelAndView modelAndView = new ModelAndView("product/product_add");

        modelAndView.addObject("categoryMap", getCategoryMap(CategoryModelEnum.CUISINE.getCode()));

        return modelAndView;
    }

    /**
     * @Title: 添加/修改商品信息
     * @Param
     * @Return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultVO save(@Valid ProductInfoForm infoForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.ORDER_FORM_ERRO.getMessage());
        }

        ProductInfo productInfo = new ProductInfo();

        try {

            //判断id是否存在
            if (!StringUtils.isEmpty(infoForm.getProductId())) { //修改

                productInfo = productInfoService.findOne(infoForm.getProductId());
                infoForm.setProductStatus(ProductStatusEnum.UP.getCode());

            } else {//新建
                // productInfo.setProductId(IDUtils.genUniqueKey());
                infoForm.setProductId(IDUtils.genUniqueKey());
                infoForm.setProductStatus(ProductStatusEnum.UP.getCode());
            }

            BeanUtils.copyProperties(infoForm, productInfo);

            productInfo.setIfCombo(0);
            productInfoService.save(productInfo);

        } catch (Exception e) {

            //throw new MyRuntimeException(ResultEnum.PRODUCT_FORM_SUBMIT_ERROR.getMessage());
            return ResultVOUtils.error(400, e.getMessage());

        }

        return ResultVOUtils.success();

    }

    /**
     * @Title: toEidt 编辑商品信息
     * @Param [productId, map]
     * @Return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEidt(@PathVariable(value = "id") String productId
            , Map<String, Object> map) {

        ModelAndView modelAndView = new ModelAndView("product/product_edit");
        List<ProductCategory> cuisineCategoryList = categoryService.findAllBycategomodeAndCategoryModel(CategoryModelEnum.CUISINE.getCode());

        ProductInfo productInfo = productInfoService.findOne(productId);

        modelAndView.addObject("productInfo", productInfo);
        modelAndView.addObject("categoryMap", getCategoryMap(CategoryModelEnum.CUISINE.getCode()));

        return modelAndView;
    }


    /**
     * @Title: delete 根据ID删除商品信息
     * @Param [id]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultVO delete(@RequestParam("id") String id) {

        try {
            productInfoService.deleteInfo(id);

        } catch (Exception e) {
            return ResultVOUtils.error(400, "删除失败:" + e.getMessage());
        }

        return ResultVOUtils.success();

    }

    /**
     * @Title: 根据传送过得商品id段获得名称
     * @Param "123,456"
     * @Return "菜1,菜2"
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public ResultVO getProductInfo(String id) {

        String[] bytes = id.split(",");

        StringBuffer namebuffer = new StringBuffer();

        for (String aByte : bytes) {

            ProductInfo info = productInfoService.findOne(aByte);

            namebuffer.append(info.getProductName()).append(",");

        }

        return ResultVOUtils.success(namebuffer.toString().substring(0, namebuffer.lastIndexOf(",")));

    }

    /**
     * @Title: 修改状态
     * @Param
     * @Return
     */
    @RequestMapping("/editStatus")
    @ResponseBody
    public ResultVO editStatus(@RequestParam("id") String id) {

        ProductInfo info = productInfoService.findOne(id);

        if (info == null) {
            return ResultVOUtils.error(400, ResultEnum.PRODUCT_COMBO_NOT_EXIST.getMessage());
        }

        Integer productStatus = info.getProductStatus();

        if (productStatus == 0) {
            info.setProductStatus(1);
        } else {
            info.setProductStatus(0);
        }

        ProductInfo save = productInfoService.save(info);

        if (save == null) {
            return ResultVOUtils.error(400, ResultEnum.PRODUCT_STATUS_EDIT_FAIL.getMessage());
        }

        return ResultVOUtils.success();

    }

    /**
     * @Title: 查询所有类目
     * @Param
     * @Return
     */
    private Map<String, Object> getCategoryMap(Integer categoryModel) {
        //查询出所有类目
        // List<ProductCategory> categoryList = categoryService.findAll(new Sort(Sort.Direction.ASC, "categorySerial"));

        Map<String, Object> params = new HashMap<>();

        Map<Integer, List<ProductCategory>> categorySerial = categoryService.findAllOrderByCategoryType(new Sort(Sort.Direction.ASC, "categorySerial"),categoryModel);



        for (Map.Entry entry : categorySerial.entrySet()) {

            params.put(entry.getKey() + "", entry.getValue());

        }

        return params;
    }

}
