package com.bj.pay.controller;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.service.OrderMasterService;
import com.bj.pay.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderMasterService orderService;
    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //1.查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            log.info("【支付】订单查询，订单为{}", orderDto);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.cerate(orderDto);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }
    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        log.info("notifyData:{}",notifyData);
        payService.notify(notifyData);

        //返回给微信处理结果
        //String string="";
        return new ModelAndView("pay/success");
    }
}
