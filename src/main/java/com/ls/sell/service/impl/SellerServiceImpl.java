package com.ls.sell.service.impl;

import com.ls.sell.pojo.SellerInfo;
import com.ls.sell.repository.SellerInfoRepository;
import com.ls.sell.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: SellerServiceImpl
 * @description: 卖家service
 * @author: liusCoding
 * @create: 2020-03-06 09:59
 */
@Service
public class SellerServiceImpl implements ISellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 通过openid  查询 卖家用户信息
     *
     * @param openid 微信用户唯一标识
     * @date: 2020/3/6
     * @return: com.ls.sell.pojo.SellerInfo
     **/
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
