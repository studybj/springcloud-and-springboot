package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Image;
import com.bj.wechat.entity.res.msg.ImageMessage;
import com.bj.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 图片类消息的处理类
 * @author 白健
 */
@Slf4j
public class ImageService {

    public static String processRequest(Map<String, String> requestMap, String url) {
        String msg = "";
        ImageMessage imageMessage  = new ImageMessage();
        BeanUtils.copyProperties(MessageService.backInfo(requestMap), imageMessage);
        imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
        Image image = new Image();
        image.setMediaId(requestMap.get("MediaId"));
        if(!StringUtils.isEmpty(url)){
            image.setMediaId(url);
        }
        imageMessage.setImage(image);
        msg = MessageUtil.ImageMessageToXml(imageMessage);
        return msg;
    }
}
