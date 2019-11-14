package com.bj.pay.service.impl;

import com.bj.eat.dto.OrderDto;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.service.OrderMasterService;
import com.bj.pay.service.PayService;
import com.bj.utils.JsonUtil;
import com.bj.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "订单名称";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderMasterService orderService;
    @Override
    public PayResponse cerate(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDto.getId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付状态
        //3. 支付金额
        //4. 支付人（下单人==支付人）
        PayResponse payResponse=bestPayService.asyncNotify(notifyData);//可以完成1、2两步
        log.info("【微信支付 异步通知】，payResponse={}",JsonUtil.toJson(payResponse));

        //查询订单
        OrderDto orderDto=orderService.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if(orderDto==null){
            log.error("【微信支付】 异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致(0.10   0.1)
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDto.getOrderAmount().doubleValue())){
            log.error("【微信支付】 异步通知，订单金额不一致，orderId={},微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDto.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY);
        }
        //修改订单的支付状态
        orderService.paid(orderDto);

        return payResponse;
    }
    /**
     * 退款
     * @param orderDto
     */
    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderId(orderDto.getId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request={}",JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse=bestPayService.refund(refundRequest);
        log.info("【微信退款】 response={}",JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
