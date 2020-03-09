package com.ls.sell.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ls.sell.SellApplicationTests;
import com.ls.sell.pojo.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ISellerServiceTest extends SellApplicationTests {

    @Autowired
    private ISellerService sellerService;

    private String openid = "111";

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        log.info("【通过openid查询用户信息】result:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        Assert.assertNotNull(result);
    }
}