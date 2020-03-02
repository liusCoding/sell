package com.ls.sell.utils;

import java.util.Random;

/**
 * @program: sell->KeyUtil
 * @description: 唯一的主键
 * @author: liushuai
 * @create: 2019-10-28 08:05
 **/

public class KeyUtil {


    /**
     * 生成唯一的主键
     * 格式： 时间 + 随机数
     *
     * @author liusCoding
     * @return String
     */
    public static synchronized String getUniqueKey() {

        Random random = new Random();
        Integer number= random.nextInt(900000) + 100000;

        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
