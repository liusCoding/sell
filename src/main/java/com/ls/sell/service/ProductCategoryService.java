package com.ls.sell.service;
import com.ls.sell.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findById(Integer categoryId);

    List <ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagorytypes);

    ProductCategory save(ProductCategory productCategory);


}
