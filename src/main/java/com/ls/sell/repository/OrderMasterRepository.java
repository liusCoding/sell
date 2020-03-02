package com.ls.sell.repository;

import com.ls.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liusCoding
 *
 * OrderMaster 主仓库类
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {


    /**
     * 根据买家openid获取买家订单
     * @author liushuai
     * @param openid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);
}
