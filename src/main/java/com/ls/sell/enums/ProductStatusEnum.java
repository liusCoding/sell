package com.ls.sell.enums;

import lombok.Getter;

/**
 * @author liusCoding
 * @decription 商品状态
 */

@Getter
public enum ProductStatusEnum implements CodeEnums<Integer>{
    UP(0,"在架"),

    DOWN(1,"下架") ;


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
