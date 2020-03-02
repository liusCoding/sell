package com.ls.sell.service;

import com.ls.sell.pojo.ProductInfo;
import com.ls.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品业务
 * @author liushuai
  * @param
 * @return
 */
public interface IProductInfoService {

    /**
     *通过id查找商品信息
     * @author liushuai
      * @param productId
     * @return
     */
    ProductInfo findById(String productId);

    /**
     *查询所有上架的商品
     * @author liushuai
     * @param
     * @return List<ProductInfo>
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询
     * @author liushuai
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     *  保存商品
     * @author liushuai
     * @param productInfo
     * @return ProductInfo
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     *  加库存
     * @author liushuai
     * @param cartDTOList
     * @return
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减去库存
     * @author liushuai
     * @param cartDTOList
     * @return
     */
    void decreaseStock(List<CartDTO> cartDTOList);



}
