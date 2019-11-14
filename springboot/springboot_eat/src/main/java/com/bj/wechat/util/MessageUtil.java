package com.bj.wechat.util;

import com.bj.wechat.entity.res.model.*;
import com.bj.wechat.entity.res.msg.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 */
public class MessageUtil {
    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    /**
     * 返回消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /**
     * 返回消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 返回消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 请求消息类型：小视频
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：VIEW(自定义菜单点击事件,跳转链接)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";
    /**
     * 事件类型：SCAN(自定义菜单点击事件,扫描带参数二维码事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /**
     * 事件类型：LOCATION(自定义菜单点击事件,上报地理位置事件)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /**
     * 事件类型：scancode_push(自定义菜单点击事件,扫码推事件的事件推送)
     */
    public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
    /**
     * 事件类型：scancode_waitmsg(自定义菜单点击事件，扫码推事件且弹出“消息接收中”提示框的事件推送)
     */
    public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 事件类型：pic_sysphoto(自定义菜单点击事件,弹出系统拍照发图的事件推送)
     */
    public static final String EVENT_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 事件类型：pic_photo_or_album(自定义菜单点击事件,弹出拍照或者相册发图的事件推送)
     */
    public static final String EVENT_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 事件类型：pic_weixin(自定义菜单点击事件,弹出微信相册发图器的事件推送)
     */
    public static final String EVENT_TYPE_PIC_WEIXIN = "pic_weixin";
    /**
     * 事件类型：location_select(自定义菜单点击事件,弹出地理位置选择器的事件推送)
     */
    public static final String EVENT_TYPE_LOCATION_SELECT = "location_select";
    /**
     * 事件类型：view_miniprogram(自定义菜单点击事件,点击菜单跳转小程序的事件推送)
     */
    public static final String EVENT_TYPE_VIEW_MINIPROGRAM = "view_miniprogram";

    /**
     * 解析微信发来的请求（XML）
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXmlToMap(HttpServletRequest request) throws Exception {
        // 将解析结果存储在 HashMap 中
        Map<String, String> map = new HashMap<String, String>();
        // 从 request 中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到 xml 根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 文本消息对象转换成 xml
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    /**
     * 图片消息对象转换成 xml
     * @param imageMessage 图片消息对象
     * @return xml
     */
    public static String ImageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        xstream.alias("item", new Image().getClass());
        return xstream.toXML(imageMessage);
    }
    /**
     * 音乐消息对象转换成 xml
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        xstream.alias("item", new Music().getClass());
        return xstream.toXML(musicMessage);
    }
    /**
     * 图文消息对象转换成 xml
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }
    /**
     * 视频消息对象转换成 xml
     * @param videoMessage 视频或小视频消息对象
     * @return xml
     */
    public static String VideoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        xstream.alias("item", new Video().getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音频消息对象转换成 xml
     * @param voiceMessage 音频消息对象
     * @return xml
     */
    public static String VoiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        xstream.alias("item", new Voice().getClass());
        return xstream.toXML(voiceMessage);
    }
    /**
     * 扩展 xstream，使其支持 CDATA 块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有 xml 节点的转换都增加 CDATA 标记
                boolean cdata = true;
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
