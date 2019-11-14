package com.bj.eat.dto;

import com.bj.eat.entity.OrderDetail;
import com.bj.eat.entity.OrderMaster;
import com.bj.eat.enums.OrderPayStatusEnum;
import com.bj.eat.enums.OrderStatusEnum;
import com.bj.wechatserver.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL) 配置文件全局使用为空不显示
public class OrderDto extends OrderMaster {
    private List<OrderDetail> orderDetails;

   /* @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(getOrderStatus().toString(),OrderStatusEnum.class);
    }

    @JsonIgnore
    public OrderPayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(getPayStatus().toString(),OrderPayStatusEnum.class);
    }*/
}
