package com.ls.sell.repository;

import com.ls.sell.dataobject.OrderMaster;
import com.ls.sell.enums.OrderStatusEnums;
import com.ls.sell.enums.PayStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster order = OrderMaster.builder()
                .orderId("10011").buyerName("liusCoding")
                .buyerAddress("商报社大厦").buyerPhone("95508")
                .buyerOpenid("abc123").orderAmount(new BigDecimal(88.88))
                .orderStatus(OrderStatusEnums.NEW.getCode())
                .payStatus(PayStatusEnums.NEW.getCode()).build();

        OrderMaster result = repository.save(order);

        Assert.assertEquals("10012",result.getOrderId());

    }


    @Test
    public void findByOpenIdTest(){
        Pageable page = new PageRequest(0, 2);
        Page<OrderMaster> result = repository.findByBuyerOpenid("abc123", page);

        Assert.assertNotEquals(0,result.getContent().size());
    }
}