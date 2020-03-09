package com.ls.sell.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ls.sell.enums.OrderStatusEnums;
import com.ls.sell.enums.PayStatusEnums;
import com.ls.sell.pojo.OrderDetail;
import com.ls.sell.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

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
    //@JsonSerialize(using = DateToLongSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /** 更新时间 */
    //@JsonSerialize(using = DateToLongSerializer.class)
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private List<OrderDetail> orderDetailList ;

    @JsonIgnore//序列化忽略字段
    public OrderStatusEnums getOrderStatusEnum() {
      return EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnumsEnum() {
        return EnumUtil.getByCode(payStatus,PayStatusEnums.class);
    }

}
