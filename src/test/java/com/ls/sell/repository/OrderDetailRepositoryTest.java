package com.ls.sell.repository;


import com.ls.sell.pojo.OrderDetail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    @Test
    public void saveTest(){
        OrderDetail orderDetail = OrderDetail.builder()
                .orderDetailId("12345676").orderId("10011")
                .productId("123").productName("香辣小龙虾")
                .productPrice(new BigDecimal(88.8)).productQuantity(2)
                .productIcon("http://xxx.jpg").build();

        OrderDetail result = repository.save(orderDetail);

        Assert.assertEquals("12345676",result.getOrderDetailId());
    }


    @Test
    public void findByOrderIdTest(){
        List<OrderDetail> result = repository.findByOrderId("10011");

        Assert.assertNotEquals(0,result.size());
    }
}