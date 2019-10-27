package com.ls.sell.dataobject;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell->ProductInfo
 * @description: 商品实体
 * @author: liushuai
 * @create: 2019-08-04 07:35
 **/

@Entity
@Data
public class ProductInfo {
    /** 商品ID */
    @Id
    private String productId;

    /** 商品名字 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    /** 商品的状态  0正常  1下架*/
    private Integer productStatus;

    /** 类目编号*/
    private Integer categoryType;


}
