package com.bj.eat.serviceimpl;

import com.bj.eat.dao.ProductCategoryRepository;
import com.bj.eat.entity.ProductCategory;
import com.bj.eat.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).get();
    }
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        ProductCategory pc = productCategoryRepository.findById(productCategory.getId()).get();
        pc = productCategory;
        return productCategoryRepository.save(pc);
    }
}
