package com.ls.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: sell->ProductInfoVO
 * @description:
 * @author: liushuai
 * @create: 2019-08-04 16:59
 **/
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDesciption;
    @JsonProperty("icon")
    private String productIcon;
}
