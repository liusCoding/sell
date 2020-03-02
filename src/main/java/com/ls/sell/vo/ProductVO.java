package com.ls.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * @program: sell->ProductVO
 * @description: 商品VO
 * @author: liushuai
 * @create: 2019-08-04 16:56
 **/

@Data
public class ProductVO {

    //序列化后的名称
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
