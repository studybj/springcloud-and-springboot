package com.bj.eat.enums;

import lombok.Getter;

@Getter
public enum OrderUpdateEnum {
    CANCEL(0,"取消"),
    PAID(1,"支付"),
    FINISH(2,"完成")
    ;
    private Integer code;
    private String message;

    OrderUpdateEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
