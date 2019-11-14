package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Image;
import com.bj.wechat.entity.res.msg.ImageMessage;
import com.bj.wechat.entity.res.msg.NewsMessage;
import com.bj.wechat.entity.res.msg.TextMessage;
import com.bj.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 地理位置类消息的回复处理类
 * @author 白健
 */
@Slf4j
public class LocationService {

    public static String processRequest(Map<String, String> requestMap) {
        String msg = "";
        //根据地理位置回复附近的，返回图文消息
        msg = MessageUtil.newsMessageToXml(new NewsMessage());
        return msg;
    }
}
