package com.ls.sell.service;

import com.ls.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author liusCoding
 *
 * 订单业务层
 */
public interface IOrderService {

    /**
     *  创建订单
     * @author liushuai
     * @param orderDTO
     * @return OrderDTO
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @author liushuai
     * @param orderId
     * @return OrderDTO
     */
    OrderDTO findOne(String orderId);

    /**
     * 通过买家微信openid 查询订单列表
     * @author liushuai
     * @param buyerOpenid
     * @param pageable
     * @return Page<OrderDTO>
     */
    Page<OrderDTO>  findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @author liushuai
     * @param orderDTO
     * @return OrderDTO
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     * @author liushuai
     * @param orderDTO
     * @return  OrderDTO
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @author liushuai
     * @param orderDTO
     * @return OrderDTO
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     * @author liushuai
     * @param pageable
     * @return Page<OrderDTO>
     */
    Page<OrderDTO> findList(Pageable pageable);

}
