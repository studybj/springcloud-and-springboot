package com.bj.eat.serviceimpl;


import com.bj.eat.entity.ProductInfo;
import com.bj.eat.enums.ProductStatusEnum;
import com.bj.eat.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService service;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = service.findOne("123456");
        Assert.assertEquals("123456",productInfo.getId());
    }
    @Test
    public void findAll() throws Exception {
        List<ProductInfo> list = service.findAll();
        Assert.assertEquals(1,list.size());
    }
    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = service.findUpAll();
        Assert.assertEquals(0,list.size());
    }
    @Test
    public void findAll1() throws Exception {
        PageRequest page = new PageRequest(0,2);
        Page<ProductInfo> list = service.findAll(page);
        System.out.println(list.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId("123457");
        productInfo.setProductName("蛋炒饭");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductDescription("很好吃的蛋炒饭");
        productInfo.setProductIcon("http://xxx/xx/abc.jpg");
        productInfo.setProductStock(5);
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(1);
        productInfo.setCreateTime(new Date());
        ProductInfo pp = service.save(productInfo);
        Assert.assertEquals("123457",pp.getId());
    }

    @Test
    public void update() throws Exception {
    }

}