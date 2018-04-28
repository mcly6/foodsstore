package com.shuxin.foodsstore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
* @ClassName: ProductVO
* @Description: TODO(商品[包含类目].)
* @author liu yang  
* @date 2017/12/21 14:17  
*/ 
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("serial")
    private Integer categorySerial;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;




}
