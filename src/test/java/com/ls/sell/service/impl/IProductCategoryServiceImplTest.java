package com.ls.sell.service.impl;

import com.ls.sell.pojo.ProductCategory;
import com.ls.sell.service.IProductCategoryService;
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
public class IProductCategoryServiceImplTest {

    @Autowired
    private IProductCategoryService IProductCategoryService;

    @Test
    public void findById() {
        ProductCategory result = IProductCategoryService.findById(1);

        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = IProductCategoryService.findAll();

        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categories = Arrays.asList(1, 3, 4);
        List<ProductCategory> result = IProductCategoryService.findByCategoryTypeIn(categories);

        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {

        ProductCategory productCategory = new ProductCategory("5折起", 20);

        ProductCategory result = IProductCategoryService.save(productCategory);

        Assert.assertNotNull(result);
    }
}