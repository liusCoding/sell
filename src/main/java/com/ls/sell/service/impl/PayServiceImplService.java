package com.ls.sell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.service.IOrderService;
import com.ls.sell.service.IPayService;
import com.ls.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @className: PayServiceImplService
 * @description:
 * @author: liusCoding
 * @create: 2020-03-02 15:21
 */
@Service
@Slf4j
public class PayServiceImplService implements IPayService {
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private IOrderService orderService;

    private static final String ORDER_NAME = "微信点餐订单";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
        log.info("【微信支付】 发起支付，request={}",JSON.toJSONString(payRequest, SerializerFeature.PrettyFormat));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 发起支付，request={}:{}", JSON.toJSONString(payResponse, SerializerFeature.PrettyFormat));
        return payResponse;
    }

    /**
     * 微信异步通知处理
     *
     * @param notifyData 异步通知数据
     * @date: 2020/3/2
     * @return: void
     **/
    @Override
    public PayResponse notify(String notifyData) {
        //异步通知需要处理的
        //1.验证签名

        //2.支付的状态

        //3.支付的金额

        //4.支付人（下单人==支付人）可不做

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",JSON.toJSONString(notifyData,SerializerFeature.PrettyFormat));

        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(Objects.isNull(orderDTO)){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEunm.ORDER_NOT_EXIST);
        }

        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，金额不一致，orderId={},支付金额={}，系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());

            throw new SellException(ResultEunm.WXPAY_NOTFIY_MOENY_VERIFY_ERROR);
        }

        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 订单退款
     *
     * @param orderDTO 订单DTO
     * @date: 2020/3/3
     * @return: void
     **/
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
        log.info("【微信退款】：request:{}",JSON.toJSONString(refundRequest,SerializerFeature.PrettyFormat));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);

        log.info("【微信退款】：response:{}",JSON.toJSONString(refundResponse,SerializerFeature.PrettyFormat));
        return refundResponse;
    }
}
