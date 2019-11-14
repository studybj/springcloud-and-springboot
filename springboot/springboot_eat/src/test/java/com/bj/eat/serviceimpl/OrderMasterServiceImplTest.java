package com.bj.eat.serviceimpl;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.entity.OrderDetail;
import com.bj.eat.service.OrderMasterService;
import com.bj.eat.service.ProductCategoryService;
import com.bj.eat.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {
    @Autowired
    OrderMasterService service;
    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        detailList.add(orderDetail);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123457");
        orderDetail1.setProductQuantity(1);
        detailList.add(orderDetail1);

        orderDto.setBuyerOpenId("123456");
        orderDto.setBuyerAddress("深圳市宝安区");
        orderDto.setBuyerName("张昌利");
        orderDto.setBuyerPhone("13212345678");
        orderDto.setCreateTime(new Date());
        orderDto.setId(KeyUtil.genUniqueKey());
        orderDto.setOrderDetails(detailList);

        service.create(orderDto);
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}