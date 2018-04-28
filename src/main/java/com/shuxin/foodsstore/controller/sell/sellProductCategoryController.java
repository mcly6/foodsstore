package com.shuxin.foodsstore.controller.sell;

import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.ProductCategory;
import com.shuxin.foodsstore.entity.ProductInfo;
import com.shuxin.foodsstore.service.ProductCategoryService;
import com.shuxin.foodsstore.service.ProductComboService;
import com.shuxin.foodsstore.service.ProductInfoService;
import com.shuxin.foodsstore.vo.ProductInfoVO;
import com.shuxin.foodsstore.vo.ProductNumSessionVO;
import com.shuxin.foodsstore.vo.ProductVO;
import com.shuxin.foodsstore.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sell")
public class sellProductCategoryController {

    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductComboService comboService;


    @GetMapping("/list")
    public ResultVO list(HttpServletRequest request) {


        //查询session
        List<ProductNumSessionVO> sessionVOList = (List) request.getSession().getAttribute("sessionVOList");


        //1. 查询所有上架商品
        List<ProductInfo> productInfoList = infoService.findUpAll();

        //2. 查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        //查询所有分类的类目集合
       // List<ProductCategory> productCategoryList = categoryService.findbyCategorySerialIn(categoryTypeList);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryIdIn(categoryTypeList);


        //3. 数据拼装

        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory category : productCategoryList) {

            ProductVO productVO = new ProductVO();
            productVO.setCategorySerial(category.getCategorySerial());
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == category.getCategoryId()) {

                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);


                    //拼装session中的数据
                    if (sessionVOList != null && !sessionVOList.isEmpty()) {
                        for (ProductNumSessionVO sessionVO : sessionVOList) {

                            if (sessionVO != null) {

                                if (productInfo.getProductId().equals(sessionVO.getId())) {

                                    productInfoVO.setNum(sessionVO.getNum());
                                    productInfoVO.setTotalNum(sessionVO.getTotalNum());

                                }
                            }
                            //TODO(五星标记数量)

                        }

                    }


                    productInfoVOList.add(productInfoVO);
                }

            }

            productVO.setProductInfoVOList(productInfoVOList);


            productVOList.add(productVO);


        }

        //根据类目分类分为两个list;

        Map<Integer, List<ProductVO>> map = productVOList.stream().collect(Collectors.groupingBy(ProductVO::getCategoryType));



        return ResultVOUtils.success(map);

    }

    /**
     * @Title: 订餐页面点击商品详情是保存要下单数量
     * @Param
     * @Return
     */
    @RequestMapping("/setProductNum")
    public ResultVO setProductNum(@RequestParam("id") String id,
                                  @RequestParam("num") String num,
                                  @RequestParam("totalNum") String totalNum,
                                  HttpServletRequest request) {


        List<ProductNumSessionVO> sessionVOList = (List) request.getSession().getAttribute("sessionVOList");

        ProductNumSessionVO sessionVO = new ProductNumSessionVO();
        sessionVO.setId(id);
        sessionVO.setNum(Integer.parseInt(num));
        sessionVO.setTotalNum(Integer.parseInt(totalNum));

        sessionVOList = new ArrayList<>();

        sessionVOList.add(sessionVO);


        try {
            request.getSession().setAttribute("sessionVOList", sessionVOList);
            return ResultVOUtils.success();
        } catch (Exception e) {
            return ResultVOUtils.error(400, ResultEnum.DATA_TRANSMISSION_FAILURE.getMessage());
        }


    }

    @RequestMapping("/getProductNum")
    public ResultVO getProductNum(HttpServletRequest request) {

        try {
            List<ProductNumSessionVO> sessionVOList = (List) request.getSession().getAttribute("sessionVOList");
            ProductNumSessionVO sessionVO = null;

            if (sessionVOList != null && !sessionVOList.isEmpty()) {
                sessionVO = sessionVOList.get(sessionVOList.size() - 1);
            }

            //查询商品详细信息
            ProductInfo info = infoService.findOne(sessionVO.getId());

            ProductInfoVO infoVO = new ProductInfoVO();
            BeanUtils.copyProperties(info, infoVO);

            infoVO.setNum(sessionVO.getNum());
            infoVO.setTotalNum(sessionVO.getTotalNum());

            return ResultVOUtils.success(infoVO);
        } catch (Exception e) {
            return ResultVOUtils.error(400, ResultEnum.PRODUCT_COMBO_NOT_EXIST.getMessage());
        }


    }
}
