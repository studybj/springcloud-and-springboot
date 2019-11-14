package com.bj.pay.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:wx.properties")
@ConfigurationProperties(prefix = "wx")
public class WechatAccConfig {

    private String appId;
    private String appSecret;
    //商户号
    private String mchId;
    //商户密钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
    //同步通知地址
    private String url;
    //异步通知地址
    private String notifyUrl;
}