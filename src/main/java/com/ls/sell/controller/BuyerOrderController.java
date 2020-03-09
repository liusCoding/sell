package com.ls.sell.controller;

import com.ls.sell.converter.OrderFormToOrderDTOConverter;
import com.ls.sell.dto.OrderDTO;
import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.form.OrderFrom;
import com.ls.sell.service.IBuyerService;
import com.ls.sell.service.IOrderService;
import com.ls.sell.utils.ResultVOUtil;
import com.ls.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sell->BuyerOrderController
 * @description: 买家端Order Controller
 * @author: liushuai
 * @create: 2019-10-31 11:04
 **/

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    private final IOrderService IOrderService;

    private final IBuyerService IBuyerService;

    @Autowired
    public BuyerOrderController(IOrderService IOrderService, IBuyerService IBuyerService) {
        this.IOrderService = IOrderService;
        this.IBuyerService = IBuyerService;
    }

   /**
    * 创建订单
    * @author liushuai
    * @param orderFrom
    * @param bindingResult
    * @return ResultVO<Map<String,String>>
    */

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderFrom orderFrom, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确，orderForm={}",orderFrom);
            throw new SellException(ResultEunm.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderFrom);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEunm.CART_EMPTY);
        }

        OrderDTO createResult = IOrderService.create(orderDTO);

        Map<String,String> map = new HashMap<>(16);
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    /**
     * 根据买家微信openid  查找订单
     * @author liushuai
     * @param openid
     * @param page
     * @param size
     * @return List<OrderDTO>
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEunm.PARAM_ERROR);
        }

        PageRequest pageable = PageRequest.of(page-1, size);

        Page<OrderDTO> orderDTOPage = IOrderService.findList(openid, pageable);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 查询订单详情
     * @author liushuai
      * @param openid
     * @param orderId
     * @return OrderDTO
     */


    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        if(StringUtils.isEmpty(openid)&&StringUtils.isEmpty(orderId)){
            log.error("[查询订单详情]  openid或者 orderId 为空");
            throw new SellException(ResultEunm.PARAM_ERROR);
        }

        OrderDTO orderDTO = IBuyerService.findOrderOne(openid, orderId);

        return  ResultVOUtil.success(orderDTO);
    }

    /**
     * @Description: TODO
     * @Date: 2019/11/4
     * @Author: liusCoding
     * @param openid
     * @param orderId
     * @return: com.ls.sell.vo.ResultVO
     **/
    @PutMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        if(StringUtils.isEmpty(openid)&&StringUtils.isEmpty(orderId)){
            log.error("[查询订单详情]  openid或者 orderId 为空");
            throw new SellException(ResultEunm.PARAM_ERROR);
        }


        IBuyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
