package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Image;
import com.bj.wechat.entity.res.msg.ImageMessage;
import com.bj.wechat.entity.res.msg.NewsMessage;
import com.bj.wechat.util.ExpressUtil;
import com.bj.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 链接类消息的回复处理类
 * @author 白健
 */
@Slf4j
public class LinkService {

    public static String processRequest(Map<String, String> requestMap) {
        String msg = "";
        //根据连接回复内容
        msg = MessageUtil.newsMessageToXml(new NewsMessage());
        return msg;
    }
}
