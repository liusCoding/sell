package com.ls.sell.controller;

import com.ls.sell.enums.ResultEunm;
import com.ls.sell.exception.SellException;
import com.ls.sell.form.ProductForm;
import com.ls.sell.pojo.ProductCategory;
import com.ls.sell.pojo.ProductInfo;
import com.ls.sell.service.IProductCategoryService;
import com.ls.sell.service.IProductInfoService;
import com.ls.sell.utils.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @className: SellerProductController
 * @description: 卖家端商品
 * @author: liusCoding
 * @create: 2020-03-04 17:49
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private IProductInfoService productInfoService;

    @Autowired
    private IProductCategoryService categoryService;
    /**
     * 查询商品列表
     * @date: 2020/3/5
     * @param page 页数
     * @param size 每页的大小
     * @param map
     * @return: org.springframework.web.servlet.ModelAndView
     **/
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageable);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    /**
     * 商品上架
     * @date: 2020/3/5
     * @param productId 商品id
     * @param map
     * @return: org.springframework.web.servlet.ModelAndView
     **/
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){

        try{
            productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        map.put("msg", ResultEunm.PRODUCT_ONSALE_SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }


    /**
     * 商品下架
     * @date: 2020/3/5
     * @param productId 商品id
     * @param map
     * @return: org.springframework.web.servlet.ModelAndView
     **/
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){

        try{
            productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("msg", ResultEunm.PRODUCT_OFFSALE_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if(StringUtils.isNotBlank(productId)){
            ProductInfo productInfo = productInfoService.findById(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);

    }


    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,
                             Map<String,String> map){

        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增，
            if(StringUtils.isNotBlank(form.getProductId())){
                 productInfo = productInfoService.findById(form.getProductId());
            }else {
                form.setProductId(KeyUtil.getUniqueKey());
            }

            BeanUtils.copyProperties(form,productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}

