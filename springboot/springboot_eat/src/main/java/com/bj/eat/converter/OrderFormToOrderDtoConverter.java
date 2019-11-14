package com.bj.eat.converter;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.entity.OrderDetail;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderFormToOrderDtoConverter {
    public static OrderDto convert(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenId(orderForm.getOpenId());
        Gson gson = new Gson();
        List<OrderDetail> detailList = new ArrayList<>();
        try {
            detailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【格式转换】失败，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.FORMAT_CHANGE_ERR);
        }
        orderDto.setOrderDetails(detailList);
        return orderDto;
    }
}
