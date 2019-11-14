package com.bj.wechat.entity.tuling.req;

import lombok.Data;

@Data
public class SelfLocation {
    //所在城市
    private String city;
    //省份
    private String province;
    //街道
    private String street;
}
