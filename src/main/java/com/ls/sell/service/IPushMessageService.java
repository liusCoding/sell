package com.ls.sell.service;

import com.ls.sell.dto.OrderDTO;

/**
 * 推送消息
 * @date: 2020/3/6
 **/
public interface IPushMessageService {

    /**
     * 订单状态变更消息
     * @date: 2020/3/6
     * @param orderDTO
     * @return: void
     **/
    void orderStatus(OrderDTO orderDTO);
}
