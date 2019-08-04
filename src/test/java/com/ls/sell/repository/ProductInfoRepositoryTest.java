package com.ls.sell.repository;

import com.ls.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void testFindById(){
        Optional<ProductInfo> optional = productInfoRepository.findById("1");
        ProductInfo productInfo = optional.get();

        System.out.println(productInfo);
    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> result = productInfoRepository.findByProductStatus(0);
        System.out.println(result);

    }

    @Test
    public void testSave(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("小龙虾");
        productInfo.setProductPrice(new BigDecimal(9.9));
        productInfo.setProductIcon("xiaolongxia.jpg");
        productInfo.setCategoryType(3);
        productInfo.setProductDescription("超辣的小龙虾");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo result = productInfoRepository.save(productInfo);

        System.out.println(result);

    }
}