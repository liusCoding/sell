package com.ls.sell.enums;

import lombok.Getter;

/** 订单状态 */
@Getter
public enum OrderStatusEnums {

    NEW(1,"新订单"),
    FINSH(2,"完成订单"),
    CANEL(3,"取消订单")
    ;


    private Integer code;

    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
