package com.ls.sell.service;

import com.ls.sell.pojo.SellerInfo;

public interface ISellerService {

    /**
     *  通过openid  查询 卖家用户信息
     * @date: 2020/3/6
     * @param openid 微信用户唯一标识
     * @return: com.ls.sell.pojo.SellerInfo
     **/
    SellerInfo findSellerInfoByOpenid(String openid);
}
