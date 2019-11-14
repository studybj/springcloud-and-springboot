package com.bj.eat.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    WAIT(0,"待上架"),
    UP(1,"已上架"),
    DOWN(2,"已下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
