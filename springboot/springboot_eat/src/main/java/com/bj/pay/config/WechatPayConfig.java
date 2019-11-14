package com.bj.pay.config;


import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(WechatAccConfig.class)
public class WechatPayConfig {

    @Autowired
    private WechatAccConfig wechatAccConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5ConfigStorage());
        return bestPayService;
    }
    @Bean
    public WxPayH5Config wxPayH5ConfigStorage() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccConfig.getAppId());
        wxPayH5Config.setAppSecret(wechatAccConfig.getAppSecret());
        wxPayH5Config.setMchId(wechatAccConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatAccConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(wechatAccConfig.getNotifyUrl());
        return wxPayH5Config;
    }
}