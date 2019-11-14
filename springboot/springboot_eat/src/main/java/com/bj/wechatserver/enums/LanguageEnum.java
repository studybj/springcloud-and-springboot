package com.bj.wechatserver.enums;

import lombok.Getter;

@Getter
public enum LanguageEnum implements BaseEnum {
    A("zh_CN","简体中文"),
    B("zh_TW","繁体中文TW"),
    C("zh_HK","繁体中文HK"),
    D("en","英文"),
    E("id","印尼"),
    F("ms","马来"),
    G("es","西班牙"),
    H("ko","韩国"),
    I("it","意大利"),
    J("ja","日本"),
    K("pl","波兰"),
    L("pt","葡萄牙"),
    M("ru","俄国"),
    N("th","泰文"),
    O("vi","越南"),
    P("ar","阿拉伯语"),
    Q("hi","北印度"),
    R("he","希伯来"),
    S("tr","土耳其"),
    T("de","德语"),
    U("fr","法语");

    private String code;
    private String message;
    LanguageEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
