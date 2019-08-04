package com.ls.sell.repository;
import com.ls.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindById(){
        Optional<ProductCategory> optional = productCategoryRepository.findById(1);
        ProductCategory productCategory = optional.get();
        System.out.println(productCategory.toString());
    }

    @Test
    //测试完成之后回滚
   // @Transactional
    public void testSave(){
        ProductCategory productCategory = new ProductCategory("男生最爱",4);

        ProductCategory result = productCategoryRepository.save(productCategory);

        Assert.assertNotNull(result);
    }

    @Test
    public void testFindByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,result.size());
    }
}