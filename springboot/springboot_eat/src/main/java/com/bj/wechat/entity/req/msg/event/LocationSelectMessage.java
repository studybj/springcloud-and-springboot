/**
 * 
 */
package com.bj.wechat.entity.req.msg.event;

import lombok.Data;

/**
 * 请求消息之菜单点击事件->弹出地理位置选择器的事件推送
 * @author 白健
 * event pic_weixin
 */
@Data
public class LocationSelectMessage extends ClickMessage {
    //发送的位置信息
    private String SendLocationInfo;
    //X坐标信息
    private String Location_X;
    //Y坐标信息
    private String Location_Y;
    //精度，可理解为精度或者比例尺、越精细的话 scale越高
    private String Scale;
    //地理位置的字符串信息
    private String Label;
    //朋友圈POI的名字，可能为空
    private String Poiname;
}
