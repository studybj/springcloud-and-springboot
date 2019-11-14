package com.bj.eat.controller;

import com.bj.eat.converter.OrderFormToOrderDtoConverter;
import com.bj.eat.dto.OrderDto;
import com.bj.eat.enums.OrderUpdateEnum;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.form.OrderForm;
import com.bj.eat.service.BuyerService;
import com.bj.eat.service.OrderMasterService;
import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;
    /**创建订单
     * @param orderForm 表单数据及验证
     * @param bindingResult 表单数据验证结果
     * @return
     */
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】，参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFormToOrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【创建订单】，购物车不能为空，orderDetail={}",orderDto.getOrderDetails());
            throw new SellException(ResultEnum.CART_NOT_NULL);
        }
        orderDto.setCreateTime(new Date());
        OrderDto createResult = orderMasterService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getId());
        return ResultVoUtil.success(map);
    }
    //订单列表
    @GetMapping("list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openId)){
            log.error("【查询订单列表】，openId为空");
            throw new SellException(ResultEnum.OPENID_IS_NULL);
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDto> orderDtoPage = orderMasterService.findList(openId,pageRequest);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVo<List<OrderDto>> detail(@RequestParam("openId") String openId,
                                         @RequestParam(value = "orderId") String orderId){
        if(StringUtils.isEmpty(openId)){
            log.error("【查询订单详情】，openId为空");
            throw new SellException(ResultEnum.OPENID_IS_NULL);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】，订单号为空");
            throw new SellException(ResultEnum.ORDER_IS_NULL);
        }
        OrderDto orderDto = buyerService.findOrderOne(openId, orderId);
        return ResultVoUtil.success(orderDto);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openId") String openId,
                                           @RequestParam(value = "orderId") String orderId){
        if(StringUtils.isEmpty(openId)){
            log.error("【查询订单详情】，openId为空");
            throw new SellException(ResultEnum.OPENID_IS_NULL);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】，订单号为空");
            throw new SellException(ResultEnum.ORDER_IS_NULL);
        }
        buyerService.updateOrder(openId,orderId, OrderUpdateEnum.CANCEL.getCode());
        return ResultVoUtil.success();
    }
    //支付订单
    @PostMapping("/paid")
    public ResultVo paid(@RequestParam("openId") String openId,
                           @RequestParam(value = "orderId") String orderId){
        if(StringUtils.isEmpty(openId)){
            log.error("【支付订单】，openId为空");
            throw new SellException(ResultEnum.OPENID_IS_NULL);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("【支付订单】，订单号为空");
            throw new SellException(ResultEnum.ORDER_IS_NULL);
        }
        buyerService.updateOrder(openId,orderId, OrderUpdateEnum.PAID.getCode());
        return ResultVoUtil.success();
    }
    //完成订单
    @PostMapping("/finish")
    public ResultVo finish(@RequestParam("openId") String openId,
                           @RequestParam(value = "orderId") String orderId){
        if(StringUtils.isEmpty(openId)){
            log.error("【完成订单】，openId为空");
            throw new SellException(ResultEnum.OPENID_IS_NULL);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("【完成订单】，openId为空");
            throw new SellException(ResultEnum.ORDER_IS_NULL);
        }
        buyerService.updateOrder(openId,orderId, OrderUpdateEnum.FINISH.getCode());
        return ResultVoUtil.success();
    }
}