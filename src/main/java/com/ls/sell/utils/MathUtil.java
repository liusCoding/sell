package com.ls.sell.utils;

/**
 * @className: MathUtil
 * @description:
 * @author: liusCoding
 * @create: 2020-03-02 20:31
 */

public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double d1,Double d2){

        double result = Math.abs(d1 - d2);
        if(result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }

    }
}
