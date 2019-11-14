package com.bj.eat.service;

import com.bj.eat.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory update(ProductCategory productCategory);
}
