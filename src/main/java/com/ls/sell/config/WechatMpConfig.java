package com.ls.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: sell->WechatConfig
 * @description: 微信配置
 * @author: liusCoding
 * @create: 2019-10-31 19:27
 **/

@Configuration
public class WechatMpConfig {

    @Autowired
    private WeichatAccountConfig weichatAccountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();

        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){

        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(weichatAccountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(weichatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
