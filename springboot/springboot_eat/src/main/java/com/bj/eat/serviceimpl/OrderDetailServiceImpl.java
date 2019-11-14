package com.bj.eat.serviceimpl;

import com.bj.eat.dao.OrderDetailRepository;
import com.bj.eat.dao.ProductInfoRepository;
import com.bj.eat.entity.OrderDetail;
import com.bj.eat.entity.ProductInfo;
import com.bj.eat.enums.ProductStatusEnum;
import com.bj.eat.service.OrderDetailService;
import com.bj.eat.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;

    @Override
    public OrderDetail findOne(String detailId) {
        return repository.findById(detailId).get();
    }
    @Override
    public List<OrderDetail> findAll() {
        return repository.findAll();
    }
    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return repository.findByOrderId(orderId);
    }
    @Override
    public Page<OrderDetail> findByOrderId(String orderId, Pageable pageable) {
        return repository.findByOrderId(orderId, pageable);
    }
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }
    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return null;
    }
}
