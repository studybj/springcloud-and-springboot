package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Image;
import com.bj.wechat.entity.res.msg.ImageMessage;
import com.bj.wechat.util.ExpressUtil;
import com.bj.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 文本类消息的处理类
 * @author 白健
 */
@Slf4j
public class ShortVideoService {

    public static String processRequest(Map<String, String> requestMap) {
        String msg = "";
        ImageMessage imageMessage  = new ImageMessage();
        BeanUtils.copyProperties(MessageService.backInfo(requestMap), imageMessage);
        imageMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
        Image image = new Image();
        image.setMediaId(requestMap.get("MediaId"));
        imageMessage.setImage(image);
        msg = MessageUtil.ImageMessageToXml(imageMessage);
        return msg;
    }
}
