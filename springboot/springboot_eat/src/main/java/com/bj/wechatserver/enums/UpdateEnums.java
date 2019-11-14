package com.bj.wechatserver.enums;

import lombok.Getter;

@Getter
public enum UpdateEnums implements BaseEnum {
    REMARK(1,"备注名"),
    SUBSCRIBE_STATUS(2,"订阅状态"),
    BLACK(3,"黑名单"),
    GROUP(4,"分组信息"),
    TAG(5,"标签信息"),;
    private Integer code;
    private String message;
    UpdateEnums(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
