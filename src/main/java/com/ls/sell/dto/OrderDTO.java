package com.ls.sell.dto;

import com.ls.sell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: sell->OrderDTO
 * @description: 订单传输对象
 * @author: liushuai
 * @create: 2019-10-28 07:44
 **/

@Data
public class OrderDTO {

    /** 订单ID */
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总额 */
    private BigDecimal orderAmount;

    /** 订单状态  默认为0新下单*/
    private Integer orderStatus ;

    /** 支付状态 默认为0未支付*/
    private Integer payStatus ;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    private List<OrderDetail> orderDetailList;

}
