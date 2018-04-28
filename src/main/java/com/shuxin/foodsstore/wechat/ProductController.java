package com.shuxin.foodsstore.wechat;

import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.service.ProductCategoryService;
import com.shuxin.foodsstore.service.ProductInfoService;
import com.shuxin.foodsstore.vo.ProductInfoVO;
import com.shuxin.foodsstore.vo.ProductVO;
import com.shuxin.foodsstore.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @ClassName: ProductController
* @Description: TODO(微信端用户商品列表.)
* @author liu yang  
* @date 2017/12/21 10:48  
*/ 
@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductInfoService productService;


    /**   
    * @Title: getProudctList 查询用户所有商品列表
    * @param []
    * @return com.shuxin.foodsstore.vo.ResultVO  
    */ 
    @RequestMapping("/list")
    public ResultVO getProudctList() {
        //1. 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryserialList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategories = categoryService.findbyCategorySerialIn(categoryserialList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategorySerial());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategorySerial())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtils.success(productVOList);
    }

}
