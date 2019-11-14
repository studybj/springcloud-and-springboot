package com.bj.eat.controller;

import com.bj.eat.entity.ProductCategory;
import com.bj.eat.entity.ProductInfo;
import com.bj.eat.service.ProductCategoryService;
import com.bj.eat.service.ProductInfoService;
import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ProductInfoVo;
import com.bj.eat.vo.ProductVo;
import com.bj.eat.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list() {
        //查询商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //获取所查询商品的类别
        List<Integer> productTypeList = new ArrayList<>();
        //传统方式
        /*for(ProductInfo productInfo : productInfoList){
            productTypeList.add(productInfo.getCategoryType());
        }*/
        //2.精简方式，java8 lambda方式获取
        productTypeList = productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        //查询类别
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(productTypeList);
        //数据拼装
        List<ProductVo> productVos = new ArrayList<>();
        for(ProductCategory productCategory : productCategories){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVos = new ArrayList<ProductInfoVo>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType()==productCategory.getCategoryType()){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    /*productInfoVo.setProductId(productInfo.getId());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());*/
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVos);
            productVos.add(productVo);
        }
       /* ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        resultVo.setData(productVos);
       return resultVo;*/
       return ResultVoUtil.success(productVos);
    }
}
