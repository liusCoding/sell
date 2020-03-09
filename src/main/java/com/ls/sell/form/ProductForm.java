package com.ls.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @className: ProductForm
 * @description:
 * @author: liusCoding
 * @create: 2020-03-05 15:58
 */
@Data
public class ProductForm {

    /** 商品id */
    private String productId;

    /** 名称 */
    @NotBlank
    private String productName;

    /** 价格 */
    @NotNull
    private BigDecimal productPrice;

    /** 库存 */
    @NotNull
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 小图 */
    private String productIcon;


    /** 类目编号 */
    @NotNull
    private Integer categoryType;

}
