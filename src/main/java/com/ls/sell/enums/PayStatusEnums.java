package com.ls.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liusCoding
 * @description 支付状态
 */

@Getter
@AllArgsConstructor
public enum PayStatusEnums implements CodeEnums<Integer> {

    NEW(0,"未支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;

    private String msg;

}
