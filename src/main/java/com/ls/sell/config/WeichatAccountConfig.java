package com.ls.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: sell->WeichatAccountConfig
 * @description: 微信账号相关配置
 * @author: liusCoding
 * @create: 2019-10-31 19:22
 **/

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeichatAccountConfig {

    /** 公众平台id */
    private String mpAppId;

     /** 公众平台秘钥 */
    private String mpAppSecret;
}
