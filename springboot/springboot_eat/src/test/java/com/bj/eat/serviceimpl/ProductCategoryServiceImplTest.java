package com.bj.eat.serviceimpl;

import com.bj.eat.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    ProductCategoryService productCategoryService;
    @Test
    public void findOne() throws Exception {
        System.out.println(productCategoryService.findOne(1));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(productCategoryService.findAll());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        System.out.println(productCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2)));
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

}