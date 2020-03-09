package com.ls.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.service.IOrderService;
import com.ls.sell.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

/**
 * @className: PayController
 * @description: 支付controller
 * @author: liusCoding
 * @create: 2020-03-02 14:50
 */

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IPayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){

        //1.查询订单
        OrderDTO orderDto = orderService.findOne(orderId);
        if(Objects.isNull(orderDto)){
            throw new SellException(ResultEunm.ORDER_NOT_EXIST);
        }

        //2.发起支付
        PayResponse payResponse = payService.create(orderDto);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);

    }

    @PostMapping
    public ModelAndView notify(@RequestBody String notifyData){

        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
