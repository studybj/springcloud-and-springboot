package com.bj.eat.converter;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterToOrderDtoConverter {
    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }
    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
