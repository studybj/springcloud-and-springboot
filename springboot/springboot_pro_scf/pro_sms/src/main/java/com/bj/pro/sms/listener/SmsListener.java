package com.bj.pro.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信消息监听类
 *
 * RabbitListener 消息监听
 * queues 监听的队列名称
 * RabbitHandler 消息处理
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void execue(Map<String, String> map){
        //调用工具类中的发送短信方法
    }

}
