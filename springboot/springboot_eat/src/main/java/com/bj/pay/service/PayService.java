package com.bj.pay.service;

import com.bj.eat.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
    PayResponse cerate(OrderDto orderDto);
    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDTO);
}
