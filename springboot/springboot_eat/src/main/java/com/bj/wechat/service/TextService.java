package com.bj.wechat.service;

import com.bj.wechat.entity.res.msg.TextMessage;
import com.bj.wechat.enums.BackTypeEnum;
import com.bj.wechat.util.ExpressUtil;
import com.bj.wechat.util.MessageUtil;
import com.bj.wechatserver.entity.KeyReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 文本类消息的处理类
 * @author 白健
 */
@Slf4j
public class TextService {

    public static String processRequest(String content, TextMessage textMessage) {
        String msg = "";
        content = content.trim();
        if (ExpressUtil.isQqFace(content)) {
            // 用户发什么QQ表情，就返回什么QQ表情
            msg = content + "  " + content;
        } else {
            msg = TuLinApiProcessService.getTulingResult(content);
        }
        textMessage.setContent(msg);
        return MessageUtil.textMessageToXml(textMessage);
        /*else if ("?".equals(content) || "？".equals(content)) {
            //msg = HelpUtil.getMainMenu();
        } else if ("微店".equals(content) || "weidian".equals(content)) {
            msg = "https://djia.daling.com/touch/?inviteCode=1912633&f=app&from=groupmessage&isappinstalled=0";
        } else if ("1".equals(content) *//*|| content.contains("天气")*//*) {
            //msg = WeatherService.getAddressWeather("乌海");
        } else if ("2".equals(content)) {
            msg = content + "<a href=\"http://4.bjweixintest.sinaapp.com/h5/index.html\">小游戏</a>";
        } else if ("3".equals(content)) {
            msg = content;
        } else if ("4".equals(content)) {
            //msg = TodayInHistoryService.getTodayInHistoryInfo();
        } else if ("5".equals(content)) {
            msg = content;
        } else if ("6".equals(content)) {
            msg = content;
        } else if ("7".equals(content)) {
            msg = content;
        } else if ("8".equals(content)) {
            msg = content;
        } else if ("9".equals(content)) {
            msg = content;
        } else if ("10".equals(content)) {
            msg = content;
        } else if ("11".equals(content)) {
            msg = content + "<a href=\"http://baidu.com\">百度搜索</a>";
        } else if (content.startsWith("翻译") || content.startsWith("translate")) {
            String keyWord = content.replaceAll("^翻译", "").replaceAll("^translate", "").trim();

           *//* if ("".equals(keyWord)) {
                msg = TranslateUtil.getTranslateUsage();
            } else {
                try {
                    msg = TranslateUtil.translateFromTo(keyWord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    //关键字回复处理
    public static String processRequest(KeyReply keyReply, Map<String, String> requestMap) {
        String msg = "";
        if(keyReply.getRtype().equals(BackTypeEnum.TEXT.getCode())){
            TextMessage textMessage = new TextMessage();
            BeanUtils.copyProperties(MessageService.backInfo(requestMap), textMessage);
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setContent(keyReply.getContext());
            msg = MessageUtil.textMessageToXml(textMessage);
        }else if(keyReply.getRtype().equals(BackTypeEnum.NEWS.getCode())){
            /*msg = MessageUtil.newsMessageToXml(NewsService.processRequest(requestMap,keyReply.getArticlelist()));
            msg = MessageUtil.textMessageToXml(textMessage);*/
        }else if(keyReply.getRtype().equals(BackTypeEnum.IMAGE.getCode())){
            msg = ImageService.processRequest(requestMap,keyReply.getImageurl());
        }else if(keyReply.getRtype().equals(BackTypeEnum.AUDIO.getCode())){
            msg = VoiceService.processRequest(requestMap,keyReply.getVoidceurl());
        }else if(keyReply.getRtype().equals(BackTypeEnum.VIDEO.getCode())){
            msg = ImageService.processRequest(requestMap,keyReply.getImageurl());
        }else{
            /*textMessage.setContent(TextService.processRequest(keyReply.getContext(), fromUserName));
            msg = MessageUtil.textMessageToXml(textMessage);*/
        }
        return msg;
    }
}
