package com.bj.eat.service;

import com.bj.eat.dto.CartDto;
import com.bj.eat.entity.ProductCategory;
import com.bj.eat.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findAll();
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
    ProductInfo update(ProductInfo productInfo);
    //库存的加减,
    void increaseStock(List<CartDto> cartList);
    void decreaseStock(List<CartDto> cartList);
    //上架，下架
    void productUp(String productId);
    void productDown(String productId);
    //批量上架，下架
    void productUpBatch(List<String> productList);
    void productDownBatch(List<String> productList);

}
