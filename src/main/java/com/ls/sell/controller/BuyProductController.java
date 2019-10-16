package com.ls.sell.controller;

import com.ls.sell.dataobject.ProductCategory;
import com.ls.sell.dataobject.ProductInfo;
import com.ls.sell.service.ProductCategoryService;
import com.ls.sell.service.ProductInfoService;
import com.ls.sell.utils.ResultVOUtil;
import com.ls.sell.vo.ProductInfoVO;
import com.ls.sell.vo.ProductVO;
import com.ls.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @program: sell->BuyProductController
 * @description: 买家商品
 * @author: liushuai
 * @create: 2019-08-04 12:03
 **/

@RestController
@RequestMapping("buyer/product")
public class BuyProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架的商品
        List<ProductInfo> productInfos = productInfoService.findupAll();

        //2.查询类目
        List<Integer> categoryTypeList = productInfos.stream().map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategorys = productCategoryService.findByCategoryTypeIn(categoryTypeList);

       //3.数据封装
        //所有类目的商品
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategorys) {
            //一个类目的编号和名字
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());


            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            //一个类目的所有商品
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO  productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOS);
            productVOList.add(productVO);
        }

       return ResultVOUtil.success(productVOList);
    }
}
