package com.ls.sell.service.impl;

import com.ls.sell.config.WeichatAccountConfig;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.service.IPushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: PushMessageServiceImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-03-06 17:45
 */

@Service
@Slf4j
public class PushMessageServiceImpl implements IPushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeichatAccountConfig accountConfig;

    /**
     * 订单状态变更消息
     *
     * @param orderDTO
     * @date: 2020/3/6
     * @return: void
     **/
    @Override
    public void orderStatus(OrderDTO orderDTO) {
       WxMpTemplateMessage templateMessage = new  WxMpTemplateMessage();
       templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
       templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Stream.of(new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", orderDTO.getBuyerPhone()),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临")
        ).collect(Collectors.toList());

        templateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信发送模板】发送失败，{}",e);
        }
    }
}
