package com.ls.sell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ls.sell.enums.ProductStatusEnum;
import com.ls.sell.pojo.ProductInfo;
import com.ls.sell.service.IProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IProductInfoServiceImplTest {

    @Autowired
    private IProductInfoService productInfoService;
    @Test
    public void findById() {

        ProductInfo result = productInfoService.findById("1");
        System.out.println(result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        log.info("【查询所有上架的产品】productInfos:{}", JSON.toJSONString(productInfos, SerializerFeature.PrettyFormat));
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() {

        PageRequest page = PageRequest.of(0,2);
        Page<ProductInfo> result = productInfoService.findAll(page);
        System.out.println(result.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234");
        productInfo.setProductName("水煮鱼");
        productInfo.setProductPrice(new BigDecimal(9.1));
        productInfo.setProductIcon("水煮鱼.jpg");
        productInfo.setCategoryType(4);
        productInfo.setProductDescription("超辣的水煮鱼");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);

        ProductInfo result = productInfoService.save(productInfo);

        System.out.println(result);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = productInfoService.onSale("1");
        log.info("【上架商品】productInfo:{}",JSON.toJSONString(productInfo,SerializerFeature.PrettyFormat));
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());
    }

    @Test
    public void offSale(){
        ProductInfo productInfo = productInfoService.offSale("1");
        log.info("【下架商品】productInfo:{}",JSON.toJSONString(productInfo,SerializerFeature.PrettyFormat));
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}