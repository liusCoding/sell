package com.ls.sell.service.impl;

import com.ls.sell.pojo.ProductCategory;
import com.ls.sell.repository.ProductCategoryRepository;
import com.ls.sell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sell->ProductCategoryServiceImpl
 * @description: 商品类目实现层
 * @author: liushuai
 * @create: 2019-08-03 23:20
 **/

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory findById(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> catagorytypes) {
        return productCategoryRepository.findByCategoryTypeIn(catagorytypes);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
