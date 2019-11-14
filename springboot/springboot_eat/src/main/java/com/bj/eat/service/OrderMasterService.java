package com.bj.eat.service;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.entity.OrderMaster;
import com.bj.eat.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderMasterService {

    /**创建订单*/
    OrderDto create(OrderDto orderDto);
    /**查询单个订单*/
    OrderDto findOne(String orderId);
    /**查询订单列表*/
    Page<OrderDto> findList(String buyerOpenId, Pageable pageable);
    /**查询所有订单列表*/
    Page<OrderDto> findList(Pageable pageable);
    /**取消订单*/
    OrderDto cancel(OrderDto orderDto);
    /**完成订单*/
    OrderDto finish(OrderDto orderDto);
    /**支付订单*/
    OrderDto paid(OrderDto orderDto);
}
