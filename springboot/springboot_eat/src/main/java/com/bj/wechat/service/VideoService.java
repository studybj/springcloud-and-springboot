package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Video;
import com.bj.wechat.entity.res.msg.VideoMessage;
import com.bj.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 视频类消息的处理类
 * @author 白健
 */
@Slf4j
public class VideoService {

    public static String processRequest(Map<String, String> requestMap) {
        Video video = new Video();
        video.setMediaId(requestMap.get("MediaId"));
        video.setTitle("请求资源");

        return processRequest(requestMap,video);
    }

    public static String processRequest(Map<String, String> requestMap, Video video) {
        String msg = "";
        VideoMessage videoMessage  = new VideoMessage();
        BeanUtils.copyProperties(MessageService.backInfo(requestMap), videoMessage);
        videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
        videoMessage.setVideo(video);
        msg = MessageUtil.VideoMessageToXml(videoMessage);
        return msg;
    }
     /* respContent = "您发送的是视频消息！";
            textMessage.setContent(respContent);
            MessageUtil.textMessageToXml(textMessage);
            */
}
