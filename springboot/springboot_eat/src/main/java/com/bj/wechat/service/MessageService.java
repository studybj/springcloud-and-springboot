package com.bj.wechat.service;

import com.bj.wechat.entity.res.msg.BaseMessage;
import com.bj.wechat.entity.res.msg.TextMessage;
import com.bj.wechat.util.MessageUtil;
import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.service.KeyReplyService;
import com.bj.wechatserver.service.UserInfoService;
import com.bj.wechatserver.service.UserTagXService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 *核心服务类
 */
@Service
@Slf4j
public class MessageService {
    @Autowired
    private KeyReplyService keyReplyService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserTagXService userTagXService;
    @Autowired
    private EventService eventService;
    /**
     * 处理微信发来的请求
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) throws Exception {
        String respMessage = null;
        // 默认返回的文本消息内容
        String respContent = "发送任意文本，我们开始聊天吧！！";

        // xml 请求解析
        Map<String, String> requestMap = MessageUtil.parseXmlToMap(request);
        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息类型
        String msgType = requestMap.get("MsgType");


        // 回复文本消息
        TextMessage textMessage = new TextMessage();
        BeanUtils.copyProperties(backInfo(requestMap), textMessage);
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        // 文本消息
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            // 接收用户发送的文本消息内容
            String content = requestMap.get("Content");
            KeyReply keyReply = keyReplyService.findByKey(content,0,1);
            if(keyReply != null){
                respMessage = TextService.processRequest(keyReply,requestMap);
            }else{
                respMessage = TextService.processRequest(content,textMessage);
            }
        }
        // 图片消息，接收到图片需先调用素材上传接口，然后返回给用户 ,pic_sysphoto???拍照功能
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            respMessage = ImageService.processRequest(requestMap,null);
        }
        // 地理位置消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            respMessage = LocationService.processRequest(requestMap);
        }
        // 链接消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            respMessage = LinkService.processRequest(requestMap);
        }
        // 音频消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            respMessage = VoiceService.processRequest(requestMap);
        }
        // 视频消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
            respMessage = VideoService.processRequest(requestMap);
        }
        // 小视频消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
            respMessage = ShortVideoService.processRequest(requestMap);
        }
        // 事件推送
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            respMessage = eventService.processRequest(requestMap);
        }
        return respMessage;
    }

    public static BaseMessage backInfo(Map<String, String> requestMap){
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setToUserName(requestMap.get("FromUserName"));
        baseMessage.setFromUserName(requestMap.get("ToUserName"));
        baseMessage.setCreateTime(new Date().getTime());
        baseMessage.setFuncFlag(0);
        return baseMessage;
    }
}
