package com.ls.sell.converter;

import com.ls.sell.pojo.OrderMaster;
import com.ls.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell->OrderMasterToOrderDTOConverter
 * @description: OrderMaster转成OrderDTO
 * @author: liushuai
 * @create: 2019-10-30 11:20
 **/
public class OrderMasterToOrderDTOConverter {

    /**
     * 将OrderMaster 转成 OrderDTO
     * @author liushuai
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    /**
     * 将OrderMastList 转成 OrderDTOList
     * @author liushuai
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(
                orderMaster -> convert(orderMaster)
        ).collect(Collectors.toList());
    }
}
