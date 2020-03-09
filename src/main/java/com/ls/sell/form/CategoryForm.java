package com.ls.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @className: CategoryForm
 * @description:
 * @author: liusCoding
 * @create: 2020-03-05 17:53
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字 */
    @NotBlank
    private String categoryName;

    /** 类目编码 */
    @NotNull
    private Integer categoryType;
}
