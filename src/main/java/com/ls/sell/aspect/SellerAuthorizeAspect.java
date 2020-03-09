package com.ls.sell.aspect;

import com.ls.sell.constants.CookieConstats;
import com.ls.sell.constants.RedisConstants;
import com.ls.sell.exception.SellerAuthorizeException;
import com.ls.sell.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @className: SellerAuthorizeAspect
 * @description:
 * @author: liusCoding
 * @create: 2020-03-06 16:42
 */

@Component
@Aspect
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Pointcut("execution(public * com.ls.sell.controller.Seller*.*(..))"+
    "&& !execution(public * com.ls.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes;
        requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //查询cookie

        Cookie cookie = CookieUtils.get(request, CookieConstats.TOKEN);
        if(Objects.isNull(null)){
            log.warn("【登录校验】 Cookie中查不到token" );
            throw new SellerAuthorizeException();
        }

        //从redis查询

        String value = redisTemplate.opsForValue().get(String.format(RedisConstants.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isBlank(value)){
            log.warn("【登录校验】 redis中查不到token" );
            throw new SellerAuthorizeException();
        }
    }
}
