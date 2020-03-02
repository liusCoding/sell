package com.ls.sell.controller;

import com.ls.sell.config.ProjectUrlConfig;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: sell->WeichatController
 * @description: 微信Controller
 * @author: liusCoding
 * @create: 2019-10-31 19:09
 **/

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeichatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String  authorize(@RequestParam("returnUrl") String returnUrl){
        //1.配置
        //2.调用方法

        String url = projectUrlConfig.getWechatMpauthorize() + "/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl,"GBK"));
        } catch (UnsupportedEncodingException e) {
            log.error("returnUrl Encode失败:{}",returnUrl);
            throw  new SellException(ResultEunm.URLENCODER_FAIL);
        }
        return "redirect:"+redirectUrl;
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        try {
            wxMpOAuth2AccessToken =  wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {

            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEunm.WECHAT_MP_ERORR.getCode(),e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:"+returnUrl+"?openid="+openId;
    }
}
