package com.bj.eat.serviceimpl;

import com.bj.eat.converter.OrderMasterToOrderDtoConverter;
import com.bj.eat.dao.OrderDetailRepository;
import com.bj.eat.dao.OrderMasterRepository;
import com.bj.eat.dao.ProductInfoRepository;
import com.bj.eat.dto.CartDto;
import com.bj.eat.dto.OrderDto;
import com.bj.eat.entity.OrderDetail;
import com.bj.eat.entity.OrderMaster;
import com.bj.eat.entity.ProductInfo;
import com.bj.eat.enums.OrderPayStatusEnum;
import com.bj.eat.enums.OrderStatusEnum;
import com.bj.eat.enums.ProductStatusEnum;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.service.OrderMasterService;
import com.bj.eat.service.ProductInfoService;
import com.bj.eat.utils.KeyUtil;
import com.bj.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.transaction.TransactionScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository repository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private PayService payService;
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal totalAmount = new BigDecimal(0);
        for(OrderDetail orderDetail : orderDto.getOrderDetails()){
            //1.查询商品(数量，价格)
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            totalAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(totalAmount);
            /*log.info("订单金额：productPrice={},quantity={},amount={},totamount={}",
                    productInfo.getProductPrice(),orderDetail.getProductQuantity(),
                    productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())),
                    totalAmount);*/
            //3.写入订单详情
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setId(orderId);
        orderDto.setOrderAmount(totalAmount);
        BeanUtils.copyProperties(orderDto,orderMaster);
        repository.save(orderMaster);
        //4.更新库存
        List<CartDto> cartDtoList = orderDto.getOrderDetails().stream()
                .map(e->new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = repository.findById(orderId).get();
        if(orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId(orderId);
        if(detailList == null || detailList.size()==0)
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetails(detailList);
        return orderDto;
    }
    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenId(buyerOpenId,pageable);
        List<OrderDto> orderDtos = OrderMasterToOrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtos,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;

    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = repository.findAll(pageable);
        List<OrderDto> orderDtos = OrderMasterToOrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtos,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        //1.判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】，订单状态不正确，orderId={},orderStatus={}",orderDto.getId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERR);
        }
        //2.修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = repository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】，订单更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.返还库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【取消订单】，订单中无商品详情，orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetails().stream()
                .map(e->new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);
        //4.若用户已付款，需要退款
        if (orderDto.getOrderStatus().equals(OrderPayStatusEnum.FINISH.getCode())){
            payService.refund(orderDto);
        }
        return orderDto;
    }
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //1.判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】，订单状态不正确，orderId={},orderStatus={}",orderDto.getId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERR);
        }
        if(!orderDto.getPayStatus().equals(OrderPayStatusEnum.FINISH.getCode())){
            log.error("【支付订单】，订单支付状态不正确，orderId={},orderPayStatus={}",orderDto.getId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAYSTATUS_ERR);
        }
        //2.修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster updateResult = repository.save(orderMaster);
        if (updateResult == null){
            log.error("【完结订单】，订单更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //1.判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】，订单状态不正确，orderId={},orderStatus={}",orderDto.getId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERR);
        }
        if(!orderDto.getPayStatus().equals(OrderPayStatusEnum.WAIT.getCode())){
            log.error("【支付订单】，订单支付状态不正确，orderId={},orderPayStatus={}",orderDto.getId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAYSTATUS_ERR);
        }
        //2.修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setPayStatus(OrderPayStatusEnum.FINISH.getCode());
        OrderMaster updateResult = repository.save(orderMaster);
        if (updateResult == null){
            log.error("【支付订单】，订单更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
}
