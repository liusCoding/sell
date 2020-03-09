package com.ls.sell.controller;

import com.ls.sell.config.ProjectUrlConfig;
import com.ls.sell.constants.CookieConstats;
import com.ls.sell.constants.RedisConstants;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.pojo.SellerInfo;
import com.ls.sell.service.ISellerService;
import com.ls.sell.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className: SellerUserController
 * @description:
 * @author: liusCoding
 * @create: 2020-03-06 11:10
 */

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private ISellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String,Object> map, HttpServletResponse response){
        //1.openid 去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);

        if(Objects.isNull(sellerInfo)){
            map.put("msg", ResultEunm.LOGIN_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token 至 redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstants.EXPIRE;
        ValueOperations<String, String> stringOps = redisTemplate.opsForValue();
        stringOps.set(String.format(RedisConstants.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        //3.设置 token 至 cookie
        CookieUtils.set(response, CookieConstats.TOKEN,token,expire);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){

        //1.从cookie 查询
        Cookie cookie = CookieUtils.get(request, CookieConstats.TOKEN);

        if(Objects.nonNull(cookie)){
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstants.TOKEN_PREFIX,cookie.getValue()));

            //3.清除cookie
            CookieUtils.set(response,CookieConstats.TOKEN,null,0);
        }

        map.put("msg",ResultEunm.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
