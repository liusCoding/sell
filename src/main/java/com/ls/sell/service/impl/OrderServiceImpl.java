package com.ls.sell.service.impl;

import com.ls.sell.converter.OrderMasterToOrderDTOConverter;
import com.ls.sell.pojo.OrderDetail;
import com.ls.sell.pojo.OrderMaster;
import com.ls.sell.pojo.ProductInfo;
import com.ls.sell.dto.CartDTO;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.OrderStatusEnums;
import com.ls.sell.enums.PayStatusEnums;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.repository.OrderDetailRepository;
import com.ls.sell.repository.OrderMasterRepository;
import com.ls.sell.service.IOrderService;
import com.ls.sell.service.IProductInfoService;
import com.ls.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: sell->OrderServiceImpl
 * @description: 订单业务层的实现类
 * @author: liushuai
 * @create: 2019-10-28 08:09
 **/

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private final IProductInfoService IProductInfoService;

    private final OrderMasterRepository orderMasterRepository;

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderServiceImpl(IProductInfoService IProductInfoService, OrderMasterRepository orderMasterRepository, OrderDetailRepository orderDetailRepository) {
        this.IProductInfoService = IProductInfoService;
        this.orderMasterRepository = orderMasterRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


    /**
     * 创建订单
     *
     * @param orderDTO
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //订单id
        String orderId = KeyUtil.getUniqueKey();

         BigDecimal orderAmount = new BigDecimal(0);
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = IProductInfoService.findById(orderDetail.getProductId());

            if (Objects.isNull(productInfo)) {
                throw new SellException(ResultEunm.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                         .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                         .add(orderAmount);

            //订单详情入库
            orderDetail.setOrderDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        //填充orderDTO 的id
        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.NEW.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())
        ).collect(Collectors.toList());


        IProductInfoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster order = orderMasterRepository.getOne(orderId);

        if(Objects.isNull(order)){
            throw new SellException(ResultEunm.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEunm.ORDERDETAIL_NOT_EXSIT);
        }

        OrderDTO  orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 通过买家微信openid 查询订单列表
     *
     * @param buyerOpenid
     * @param pageable
     * @return Page<OrderDTO>
     * @author liushuai
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEunm.ORDER_STATUS_ERROR);
        }

        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.CANEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(Objects.isNull(updateResult)){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEunm.ORDER_UPDATE_FAIL);
        }

        //3.返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情，orderDTO={}",orderDTO);
            throw new SellException(ResultEunm.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())
        ).collect(Collectors.toList());

        IProductInfoService.increaseStock(cartDTOList);


        //如果已经支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnums.SUCCESS)){
            //TODO
        }
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return OrderDTO
     * @author liushuai
     */
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 查询订单列表
     *
     * @param pageable
     * @return Page<OrderDTO>
     * @author liushuai
     */
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
}
