package com.ls.sell.controller;

import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static com.ls.sell.enums.ResultEunm.ORDER_FINISH_SUCCESS;

/**
 * @className: SellerOrderController
 * @description: 卖家端订单
 * @author: liusCoding
 * @create: 2020-03-03 17:34
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/list")
    //@PageableDefault Pageable pageable
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        Page<OrderDTO> list = orderService.findList(PageRequest.of(page-1,size));
        map.put("orderDTOPage",list);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String,Object> map){

        OrderDTO orderDTO =null;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常",e);
            map.put("msg", ResultEunm.ORDER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("msg", ResultEunm.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO =null;
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端查询订单详情】发生异常",e);
            map.put("msg", ResultEunm.ORDER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("orderDTO",orderDTO);

        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String,Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】发生异常",e);
            map.put("msg", ResultEunm.ORDER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("msg",ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success");
    }
}
