package com.ls.sell.controller;

import com.ls.sell.exception.SellException;
import com.ls.sell.form.CategoryForm;
import com.ls.sell.pojo.ProductCategory;
import com.ls.sell.service.IProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

/**
 * @className: SellerCategoryController
 * @description:
 * @author: liusCoding
 * @create: 2020-03-05 17:35
 */

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private IProductCategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){

        if(Objects.nonNull(categoryId)){
            ProductCategory productCategory = categoryService.findById(categoryId);
            map.put("category",productCategory);
        }

        return new ModelAndView("category/index",map);
    }


    /**
     * 保存/更新
     * @date: 2020/3/5
     * @param form
     * @param bindingResult
     * @param map
     * @return: org.springframework.web.servlet.ModelAndView
     **/
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult,
                             Map<String,Object> map){

        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("/common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();

        try {
            if(Objects.nonNull(form.getCategoryId())){
                productCategory = categoryService.findById(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);

            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("/common/success",map);
    }

}
