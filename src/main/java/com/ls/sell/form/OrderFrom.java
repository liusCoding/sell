package com.ls.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: sell->OrderFrom
 * @description: Order表单字段
 * @author: liushuai
 * @create: 2019-10-31 11:10
 **/

@Data
public class OrderFrom {

    /** 买家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;

    /** 买家手机号 */
    @NotEmpty
    private String phone;

    /** 买家地址 */
    @NotEmpty
    private String address;

    /** 买家微信openid */
    @NotEmpty(message = "openid必填")
    private String openid;

    /** 购物车 */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
