package com.bj.eat.dao;

import com.bj.eat.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory);
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date());
        repository.save(productCategory);
    }
    @Test
    public void UpdateTest1(){
        ProductCategory productCategory = repository.findById(2).get();
        productCategory.setCategoryType(10);
        repository.save(productCategory);
    }
}