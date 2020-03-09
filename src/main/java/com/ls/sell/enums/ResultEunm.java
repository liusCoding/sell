package com.ls.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEunm {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXSIT(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WECHAT_MP_ERORR(20,"微信公众号错误"),

    URLENCODER_FAIL(21,"url encode 失败"),

   WXPAY_NOTFIY_MOENY_VERIFY_ERROR (22,"微信异步通知支付金额校验不通过"),

    ORDER_CANCEL_SUCCESS(23,"订单取消成功"),

    ORDER_FINISH_SUCCESS(24,"订单完结成功"),

    PRODUCT_STATUS_ERROR(25,"商品状态不正确"),

    PRODUCT_ONSALE_SUCCESS(26,"商品上架成功"),

    PRODUCT_OFFSALE_SUCCESS(27,"商品下架成功"),

    LOGOUT_SUCCESS(29,"登出成功"),

    LOGIN_ERROR(28,"登录失败，登录信息不正确");
    private Integer code;

    private String message;

    ResultEunm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
