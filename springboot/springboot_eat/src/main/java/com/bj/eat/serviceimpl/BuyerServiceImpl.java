package com.bj.eat.serviceimpl;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.enums.OrderUpdateEnum;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.service.BuyerService;
import com.bj.eat.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderMasterService orderService;
    @Override
    public OrderDto findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId,orderId);
    }
    @Override //method的值只能是cancel，paid，finish
    public OrderDto updateOrder(String openId, String orderId, Integer method) {
        OrderDto orderDto = checkOrderOwner(openId,orderId);
        if(orderDto == null){
            log.error("【更新订单】，查不到该订单,openId={}",openId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (method.equals(OrderUpdateEnum.CANCEL.getCode())){
            return orderService.cancel(orderDto);
        } else if (method.equals(OrderUpdateEnum.PAID.getCode())){
            return orderService.paid(orderDto);
        }else if (method.equals(OrderUpdateEnum.FINISH.getCode())){
            return orderService.finish(orderDto);
        }else {
            log.error("【更新订单】，参数异常，method={}",method);
            throw new SellException(ResultEnum.PARAM_ERR);
        }
    }

    private OrderDto checkOrderOwner(String openId, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            return null;
        }
        if(!orderDto.getBuyerOpenId().equalsIgnoreCase(openId)){
            log.error("【订单查询】，订单的openId不一致,openId={},orderDto={}",openId,orderDto);
            throw new SellException(ResultEnum.ORDER_OPENID_ERR);
        }
        return orderDto;
    }
}
