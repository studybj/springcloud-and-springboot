package com.bj.wxtool.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * 微信
 * 参考https://blog.csdn.net/antma/article/details/79629584
 * https://github.com/Wechat-Group/WxJava/wiki/公众号开发文档，第三方sdk  WxJava
 */
@Controller
@RequestMapping("/tool/wechat")
@Slf4j
public class WechatToolController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * 网页授权
     * @param returnUrl
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1.配置
        //2.调用方法
        String url = "http://ivpj8m.natappfree.cc/tool/wechat/userInfo";
//        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
