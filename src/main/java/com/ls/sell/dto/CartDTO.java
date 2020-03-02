package com.ls.sell.dto;

import lombok.Data;

/**
 * @program: sell->CartDTO
 * @description: 购物车
 * @author: liushuai
 * @create: 2019-10-29 08:10
 **/

@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
