package com.bj.wechat.controller;

import com.bj.wechat.service.MessageService;
import com.bj.wechat.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信请求分发控制器
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private MessageService coreService;
    @RequestMapping("/")
    public void Str(){
        System.out.println("dddddddddddddd");
    }
    /**
     * 确认请求来自微信服务器,微信接入 ,分发请求  （注意此处将接入与分发请求合并，也可拆分为两个方法）
     * @param request
     * @param response
     */
    @RequestMapping(value = "/connect", method = {RequestMethod.GET, RequestMethod.POST})
    public void connect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("确认请求来自微信...."+request.getRequestURL());
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter out = response.getWriter();
        try {
            if (isGet){
                //微信加密签名
                String signature = request.getParameter("signature");
                //时间戳
                String timestamp = request.getParameter("timestamp");
                //随机数
                String nonce = request.getParameter("nonce");
                //随即字符串
                String echostr = request.getParameter("echostr");
                //通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                if(SignUtil.checkSignature(signature, timestamp, nonce)){
                    log.info("weixin server connect success");
                    out.print(echostr);
                }else{
                    log.error("failed to signature");
                }
            } else {
                //调用核心业务类接收消息、处理消息
                String respMessage = coreService.processRequest(request);
                //log.info("输出："+respMessage);
                out.write(respMessage);
                log.debug("to weixin server "+respMessage);
            }
        } catch (Exception e) {
            log.error("微信连接出错了");
        } finally {
            if (out == null){
                out.close();
            }
        }
    }

}
