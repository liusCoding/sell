package com.ls.sell.service.impl;

import com.ls.sell.dataobject.ProductCategory;
import com.ls.sell.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findById() {
        ProductCategory result = productCategoryService.findById(1);

        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = productCategoryService.findAll();

        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categorytypes = Arrays.asList(1, 3, 4);
        List<ProductCategory> result = productCategoryService.findByCategoryTypeIn(categorytypes);

        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {

        ProductCategory productCategory = new ProductCategory("美酒", 9);

        ProductCategory result = productCategoryService.save(productCategory);

        Assert.assertNotNull(result);
    }
}