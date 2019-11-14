package com.bj.eat.enums;

import lombok.Getter;

@Getter
public enum OrderPayStatusEnum {
    WAIT(0,"未支付"),
    FINISH(1,"已支付"),
    CANCEL(2,"已退款");

    private Integer code;
    private String message;
    OrderPayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
