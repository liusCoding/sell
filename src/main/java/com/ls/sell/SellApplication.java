package com.ls.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SellApplication extends SpringBootServletInitializer  {
    /**
     * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){

        return builder.sources(SellApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }


}
