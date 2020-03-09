package com.ls.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: sell->LoggerTest
 * @description: 日志测试
 * @author: liushuai
 * @create: 2019-08-03 19:53
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void testLogger() {

        String name = "liushuai";
        String password = "root";
        log.debug("debugger....");
        log.info("name:" + name + ",password: "  + password);
        log.info("name : {},password: {}", name,password);

        log.error("error");
    }

    @Test
    public void teach(){
        //定义一个基本类型的整型变量age，赋值10
        int age = 10;

        //定义一个基本类型的长整型变量time，赋值10000
        long time = 10000L;

        //定义一个基本类型的双精度浮点型变量money,赋值9.99
        double money = 9.99;

        //定义一个基本类型的字符型变量c，赋值'z'
        char c = 'z';

        //定义一个基本类型的布尔类型变量flag ,赋值 true
        boolean flag = true;
    }

}

