package com.ls.sell.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ls.sell.SellApplicationTests;
import com.ls.sell.pojo.SellerInfo;
import com.ls.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SellerInfoRepositoryTest extends SellApplicationTests {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    private String openid = "111";
    @Test
    public void save(){

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid(openid);
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());

        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        log.info("【保存用户】result:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOpenid() {

        SellerInfo result = sellerInfoRepository.findByOpenid(openid);
        log.info("【通过openid查询用户信息】result:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        Assert.assertNotNull(result);
}



}