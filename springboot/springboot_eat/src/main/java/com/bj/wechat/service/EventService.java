package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Image;
import com.bj.wechat.entity.res.msg.ImageMessage;
import com.bj.wechat.util.MessageUtil;
import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.service.KeyReplyService;
import com.bj.wechatserver.service.UserInfoService;
import com.bj.wechatserver.service.UserTagXService;
import com.bj.wechatserver.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 图片类消息的处理类
 * @author 白健
 */
@Service
@Slf4j
public class EventService {
    @Autowired
    private KeyReplyService keyReplyService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserTagXService userTagXService;

    public String processRequest(Map<String, String> requestMap) {
        // 事件类型
        String eventType = requestMap.get("Event");
        String fromUserName = requestMap.get("FromUserName");
        String msg = "";
        // 订阅
        if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
            UserInfo userInfo = WeixinUtil.getUserInfo(fromUserName);
            UserInfo olduserInfo = userInfoService.findByOpenId(fromUserName);
            if(olduserInfo != null){
                userInfo.setId(olduserInfo.getId());
            }else{
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                userInfo.setId(id);
            }
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userInfo.setSubscribe_time(new Date());
            userInfoService.save(userInfo);
            if(userInfo.getTagid_list()!=null){
                //存储
                //userTagXService.save(userInfo.getTagid_list());
            }
            //查找回复内容
            KeyReply keyReply = keyReplyService.findByKey("first", 1);
            msg = TextService.processRequest(keyReply,requestMap);
        }
        // 取消订阅
        else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE) ) {
            // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复 消息
            log.info("用户取关了该公众号");
        }
        // 自定义菜单点击事件
        else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
            // TODO 自定义菜单权没有开放，暂不处理该类消息
            String eventKey = requestMap.get("EventKey");
            System.out.println("菜单事件："+eventKey);
            //respContent = MenuService.processRequest(eventKey);
            //textMessage.setContent(respContent);
            if("11".equals(eventKey)){
                //respContent = WeatherService.getAddressWeather(null);
                //textMessage.setContent(respContent);
            }
            //respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        return msg;
    }
}
