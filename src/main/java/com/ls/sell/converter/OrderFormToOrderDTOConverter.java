package com.ls.sell.converter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.form.OrderFrom;
import com.ls.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sell->OrderFormToOrderDTOConverter
 * @description: OrderForm转成OrderDTO
 * @author: liusCoding
 * @create: 2019-10-31 11:23
 **/
@Slf4j
public class OrderFormToOrderDTOConverter {

    public static OrderDTO convert(OrderFrom orderFrom){


        //将购物车json类型转成对象集合
        Gson gson  = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = JSON.parseArray(orderFrom.getItems(), OrderDetail.class);
        }catch (Exception e){
            log.error("[对象转换错误],string = {}",orderFrom.getItems());
            throw new SellException(ResultEunm.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderDTO.builder()
                .buyerName(orderFrom.getName())
                .buyerPhone(orderFrom.getPhone())
                .buyerAddress(orderFrom.getAddress())
                .buyerOpenid(orderFrom.getOpenid())
                .orderDetailList(orderDetailList).build();
        return orderDTO;
    }
}
