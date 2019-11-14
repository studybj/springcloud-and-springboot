/**
 * 
 */
package com.bj.wechat.entity.req.msg.event;

import lombok.Data;

/**
 * 请求消息之菜单点击事件->弹出系统拍照发图的事件推送,弹出拍照或者相册发图的事件推送,弹出微信相册发图器的事件推送
 * @author 白健
 * event pic_sysphoto，pic_photo_or_album, event pic_weixin
 */
@Data
public class PicSysphotoOrPhoneAlbumMessage extends ClickMessage {
    //发送的图片信息
    private String SendPicsInfo;
    //发送的图片数量
    private int Count;
    //图片列表
    private String PicList;
    //图片的MD5值，开发者若需要，可用于验证接收到图片
    private String PicMd5Sum;
}
