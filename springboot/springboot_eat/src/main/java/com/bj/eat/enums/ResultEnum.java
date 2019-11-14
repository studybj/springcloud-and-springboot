package com.bj.eat.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(901,"商品不存在"),
    PRODUCT_STOCK_ERR(801,"库存不足"),
    ORDER_NOT_EXIST(902,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(903,"订单详情不存在"),
    ORDER_STATUS_ERR(802,"订单状态不正确"),
    ORDER_UPDATE_FAIL(803,"订单更新失败"),
    ORDER_DETAIL_EMPTY(804,"订单中无商品详情"),
    ORDER_PAYSTATUS_ERR(805,"订单支付状态不正确"),
    PARAM_ERR(806,"参数不正确"),
    FORMAT_CHANGE_ERR(807,"格式转换不正确"),
    CART_NOT_NULL(808,"购物车不能为空"),
    OPENID_IS_NULL(809,"openID为空"),
    ORDER_IS_NULL(810,"订单为空"),
    ORDER_OPENID_ERR(811,"该订单不属于当前用户"),
    WXPAY_NOTIFY_MONEY_VERIFY(812,"微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(813,"订单取消成功"),
    ORDER_FINISH_SUCCESS(814,"订单完成"),
    PRODUCT_STATUS_ISUP(815,"商品已上架"),
    PRODUCT_STATUS_ISDOWN(816,"商品已下架"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
