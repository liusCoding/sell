package com.ls.sell.service.impl;

import com.ls.sell.pojo.ProductInfo;
import com.ls.sell.service.IProductInfoService;
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
public class IProductInfoServiceImplTest {

    @Autowired
    private IProductInfoService IProductInfoService;
    @Test
    public void findById() {

        ProductInfo result = IProductInfoService.findById("1");
        System.out.println(result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = IProductInfoService.findUpAll();
        System.out.println(productInfos);
    }

    @Test
    public void findAll() {

        PageRequest page = PageRequest.of(0,2);
        Page<ProductInfo> result = IProductInfoService.findAll(page);
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

        ProductInfo result = IProductInfoService.save(productInfo);

        System.out.println(result);
    }
}