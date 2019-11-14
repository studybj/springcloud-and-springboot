package com.bj.wechat.entity.tuling.req;

import lombok.Data;

@Data
public class Perception {
    //输入参数必须包含inputText或inputImage或inputMedia！即三者不能都为空
    //	文本信息
    private InputText inputText;
    //图片信息
    private InputImage inputImage;
    //音频信息
    private InputMedia inputMedia;
    //客户端属性
    private SelfInfo selfInfo;

}
