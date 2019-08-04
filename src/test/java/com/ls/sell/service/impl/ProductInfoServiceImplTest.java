package com.ls.sell.service.impl;

import com.ls.sell.dataobject.ProductInfo;
import com.ls.sell.service.ProductInfoService;
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
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService  productInfoService;
    @Test
    public void findById() {

        ProductInfo result = productInfoService.findById("1");
        System.out.println(result);
    }

    @Test
    public void findupAll() {
        List<ProductInfo> productInfos = productInfoService.findupAll();
        System.out.println(productInfos);
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
}