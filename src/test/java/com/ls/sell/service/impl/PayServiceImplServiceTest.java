package com.ls.sell.service.impl;

import com.ls.sell.SellApplicationTests;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.service.IOrderService;
import com.ls.sell.service.IPayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PayServiceImplServiceTest extends SellApplicationTests {

    @Autowired
    private IPayService payService;

    @Autowired
    private IOrderService orderService;

    private final String orderId = "1572486124005486517";
    @Test
    public void create() {
        OrderDTO orderDto = orderService.findOne(orderId);

        payService.create(orderDto);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne(orderId);
        payService.refund(orderDTO);
    }

}