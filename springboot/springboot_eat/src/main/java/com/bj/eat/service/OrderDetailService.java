package com.bj.eat.service;

import com.bj.eat.entity.OrderDetail;
import com.bj.eat.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailService {
    OrderDetail findOne(String detailId);
    List<OrderDetail> findAll();
    List<OrderDetail> findByOrderId(String orderId);
    Page<OrderDetail> findByOrderId(String orderId, Pageable pageable);

    OrderDetail save(OrderDetail orderDetail);
    OrderDetail update(OrderDetail orderDetail);
}
