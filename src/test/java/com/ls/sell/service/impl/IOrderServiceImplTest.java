package com.ls.sell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.OrderStatusEnums;
import com.ls.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IOrderServiceImplTest {

    @Autowired
    private  OrderServiceImpl orderService;

    private final String BUYER_OPENID = "111111";

    private final String  ORDER_ID = "1572485153121295223";



    @Test
    public void create() {
        OrderDTO orderDTO = OrderDTO.builder()
                .buyerName("马城")
                .buyerAddress("福田区商报社大厦11楼")
                .buyerPhone("1511111111111")
                .buyerOpenid(BUYER_OPENID).build();

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        
        OrderDetail o1 = OrderDetail.builder()
                .productId("1234")
                .productQuantity(4).build();

        OrderDetail o2 = OrderDetail.builder()
                .productId("123")
                .productQuantity(2).build();

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result  = orderService.create(orderDTO);

        log.info("[创建订单] result{}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne("1572485153121295223");

        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());

    }

    @Test
    public void findList() {
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID,PageRequest.of(0,2));

        log.info("[通过买家微信号查询订单列表]:{}",result.getContent());
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void cancel() {

        OrderDTO orderDTO = orderService.findOne("1572486124005486517");
        OrderDTO result = orderService.cancel(orderDTO);

        Assert.assertEquals(OrderStatusEnums.CANEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }

    @Test
    public void testFindList() {
        Page<OrderDTO> result = orderService.findList(PageRequest.of(0, 2));

        log.info("查询所有的订单列表：{}", JSON.toJSONString(result.getContent(), SerializerFeature.PrettyFormat));
        Assert.assertTrue("查询所有的订单列表",result.getTotalElements()>0);
    }
}