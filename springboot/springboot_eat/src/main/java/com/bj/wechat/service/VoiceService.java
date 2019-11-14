package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Voice;
import com.bj.wechat.entity.res.msg.VoiceMessage;
import com.bj.wechat.util.ExpressUtil;
import com.bj.wechat.util.MessageUtil;
import com.bj.wechatserver.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 文本类消息的处理类
 * @author 白健
 */
@Slf4j
public class VoiceService {

    public static String processRequest(Map<String, String> requestMap) {
        String recvMessage = requestMap.get("Recognition");
        String msg = "收到的语音解析结果："+recvMessage;
        if(recvMessage != null){
            msg = TuLinApiProcessService.getTulingResult(recvMessage);
        }else{
            msg = "您说的太模糊了，能不能重新说下呢？";
        }

        String mediaId = "";
        //随机
        if(Math.random() > 0.5){
            //上传文件为永久素材，并返回media_id
            String path = BaiduVoiceService.getSoundMp3(msg,"yuyin"+Math.random());
            //TODO 调用微信上传素材，返回
            mediaId = WeixinUtil.material(1,null);
        }
        msg = processRequest(requestMap, mediaId);
        return msg;
    }

    public static String processRequest(Map<String, String> requestMap, String url) {
        String msg = "";
        VoiceMessage voiceMessage = new VoiceMessage();
        BeanUtils.copyProperties(MessageService.backInfo(requestMap), voiceMessage);
        voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
        Voice voice = new Voice();
        voice.setMediaId(url);
        voiceMessage.setVoice(voice);
        msg = MessageUtil.VoiceMessageToXml(voiceMessage);
        return msg;
    }
}
