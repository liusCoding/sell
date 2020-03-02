package com.ls.sell.service.impl;

import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.service.IBuyerService;
import com.ls.sell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @program: sell->BuyerServiceImpl
 * @description: 买家Service实现
 * @author: liusCoding
 * @create: 2019-10-31 16:32
 **/

@Service
@Slf4j
public class BuyerServiceImpl  implements IBuyerService {

    private final IOrderService IOrderService;

    @Autowired
    public BuyerServiceImpl(IOrderService IOrderService) {
        this.IOrderService = IOrderService;
    }


    /**
     * 根据openid和orderId查找订单
     *
     * @param openid
     * @param orderId
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);

    }


    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     * @author liushuai
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        if(Objects.isNull(orderDTO)){
            log.error("[取消订单]查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEunm.ORDER_NOT_EXIST);
        }

        return IOrderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {

        OrderDTO orderDTO = IOrderService.findOne(orderId);

        if(Objects.isNull(orderDTO)){
            return null;
        }

        //判断订单是不是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[查询订单]订单的openid不一致，openid = {},orderDTO={}",orderId,orderDTO);
            throw new SellException(ResultEunm.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
