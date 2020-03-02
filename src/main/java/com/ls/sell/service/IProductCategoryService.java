package com.ls.sell.service;
import com.ls.sell.pojo.ProductCategory;

import java.util.List;

public interface IProductCategoryService {

    ProductCategory findById(Integer categoryId);

    List <ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagorytypes);

    ProductCategory save(ProductCategory productCategory);


}
