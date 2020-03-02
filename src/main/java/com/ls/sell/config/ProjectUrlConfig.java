package com.ls.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: sell->ProjectUrlConfig
 * @description: url配置
 * @author: liusCoding
 * @create: 2019-11-01 14:08
 **/

@Data
@ConfigurationProperties(prefix = "url")
@Component
public class ProjectUrlConfig {

    /** 微信公众平台授权url */
    public String wechatMpauthorize;

    /** 微信开放平台授权URL */
    public String wechatOpenAuthorize;

    /** 点餐系统 */
    public String sell;
}
