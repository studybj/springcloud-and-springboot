package com.bj.wechat.enums;

import com.bj.wechatserver.enums.BaseEnum;
import lombok.Getter;

@Getter
public enum BackTypeEnum implements BaseEnum {
    TEXT("1","文本"),
    NEWS("2","图文"),
    IMAGE("3","图片"),
    AUDIO("4","音频"),
    VIDEO("5","视频");
    private String code;
    private String message;
    BackTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
