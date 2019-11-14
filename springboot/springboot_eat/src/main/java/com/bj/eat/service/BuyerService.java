package com.bj.eat.service;

import com.bj.eat.dto.OrderDto;

public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openId, String orderId);
    //更新订单(取消、支付、完成)method的值可以是cancel，paid，finish
    OrderDto updateOrder(String openId, String orderId, Integer method);
}
