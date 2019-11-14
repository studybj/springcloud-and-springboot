package com.bj.wxtool.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:wx.properties")
@ConfigurationProperties(prefix = "wx")
public class WechatAccountConfig {

    private String appId;
    private String appSecret;

}