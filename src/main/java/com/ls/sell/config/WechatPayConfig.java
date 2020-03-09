package com.ls.sell.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: WechatPayConfig
 * @description: 微信支付配置
 * @author: liusCoding
 * @create: 2020-03-02 15:48
 */

@Configuration
public class WechatPayConfig {

    @Autowired
    private WeichatAccountConfig weichatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig());
        return bestPayService;
    }

    @Bean
    public WxPayConfig wxPayConfig(){
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(weichatAccountConfig.getMpAppId());
        wxPayConfig.setAppSecret(weichatAccountConfig.getMchKey());
        wxPayConfig.setMchId(weichatAccountConfig.getMchId());
        wxPayConfig.setMchKey(weichatAccountConfig.getMchKey());
        wxPayConfig.setKeyPath(weichatAccountConfig.getKeyPath());
        wxPayConfig.setNotifyUrl(weichatAccountConfig.getNotifyUrl());
        return wxPayConfig;
    }

}
