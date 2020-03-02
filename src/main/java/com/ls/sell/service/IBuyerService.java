package com.ls.sell.service;

import com.ls.sell.dto.OrderDTO;

/**
 * @program: sell->BuyerService
 * @description: 买家Service层
 * @author: liusCoding
 * @create: 2019-10-31 16:26
 **/

public interface IBuyerService {

    /**
     *  根据openid和orderId查找订单
     * @author liushuai
     * @param openid
     * @param orderId
     * @return OrderDTO
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @author liushuai
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
