package com.ls.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.ls.sell.dto.OrderDTO;

public interface IPayService {

    /**
     * 创建支付
     * @date: 2020/3/2
     * @param orderDTO 订单DTO
     * @return: com.lly835.bestpay.model.PayResponse
     **/
    PayResponse create(OrderDTO orderDTO);

    /**
     * 微信异步通知处理
     * @date: 2020/3/2
     * @param notifyData 异步通知数据
     * @return: void
     **/
    PayResponse notify(String notifyData);

    /**
     * 订单退款
     * @date: 2020/3/3
     * @param orderDTO 订单DTO
     * @return: void
     **/
    RefundResponse refund(OrderDTO orderDTO);

}
