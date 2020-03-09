package com.ls.sell.repository;

import com.ls.sell.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    /**
     * 通过openid 查询 用户信息
     * @date: 2020/3/6
     * @param openid 微信用户唯一标识
     * @return: com.ls.sell.pojo.SellerInfo
     **/
    SellerInfo findByOpenid(String openid);
}
