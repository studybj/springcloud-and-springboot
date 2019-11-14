package com.bj.wechat.entity.tuling.req;

import lombok.Data;

@Data
public class TulingReq {
    //输入类型:0-文本(默认)、1-图片、2-音频
    private int reqType;
    //输入信息
    private Perception perception;
    //用户参数
    private TuLingUser userInfo;
}
