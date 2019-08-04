package com.ls.sell.service;

import com.ls.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品业务
 * @author liushuai
  * @param
 * @return
 */
public interface ProductInfoService {

    ProductInfo findById(String productId);

    /**
     *查询所有上架的商品
     * @author liushuai
     * @param
     * @return List<ProductInfo>
     */
    List<ProductInfo> findupAll();

    /**
     * 分页查询
     * @author liushuai
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);


    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存

}
