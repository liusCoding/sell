package com.ls.sell.exception;

import com.ls.sell.enums.ResultEunm;

/**
 * @program: sell->SellException
 * @description: 微信点餐异常处理
 * @author: liushuai
 * @create: 2019-10-30 09:33
 **/

public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEunm resultEunm){

        super(resultEunm.getMessage());

        this.code = resultEunm.getCode();
    }

    public SellException(Integer code, String message){
        super(message);

        this.code = code;
    }
}
