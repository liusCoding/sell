package com.ls.sell.repository;

import com.ls.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liusCoding
 * @description 订单详情仓库类
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单id获取订单详情
     * @author liushuai
     * @param orderId
     * @return
     */
     List<OrderDetail> findByOrderId(String orderId);
}
