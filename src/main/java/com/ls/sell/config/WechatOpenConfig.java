package com.ls.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: WechatOpenConfig
 * @description:
 * @author: liusCoding
 * @create: 2020-03-06 10:18
 */

@Configuration
public class WechatOpenConfig {

    @Autowired
    private WeichatAccountConfig weichatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpServiceImpl wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenMpConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(weichatAccountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(weichatAccountConfig.getOpenAppSecret());
        return wxMpConfigStorage;
    }
}
