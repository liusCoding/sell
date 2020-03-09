package com.ls.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: CookieUtils
 * @description: cookie工具类
 * @author: liusCoding
 * @create: 2020-03-06 15:20
 */

public class CookieUtils {


    /**
     * 设置cookie
     * @date: 2020/3/6
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @return: void
     **/
    public static void set (HttpServletResponse response,
                            String name,
                            String value,
                            int maxAge
                            ){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @date: 2020/3/6
     * @param request
     * @param name
     * @return: javax.servlet.http.Cookie
     **/
    public static Cookie get(HttpServletRequest request,String name){
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = null;
        if(Objects.nonNull(cookies)){
            cookieMap = Stream.of(cookies).collect(Collectors.toMap(Cookie::getName, Function.identity()));
        }
        return cookieMap;
    }
}
