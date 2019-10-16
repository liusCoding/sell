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
    public void test(){
        String s1 = "133335";
        String s2 = "1333122";

       // if(StringUtils.containsIgnoreCase())
    }
}

