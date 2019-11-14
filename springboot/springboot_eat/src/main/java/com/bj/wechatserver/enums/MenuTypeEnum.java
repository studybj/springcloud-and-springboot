package com.bj.wechatserver.enums;

import lombok.Getter;
import net.sf.json.JSONArray;

@Getter
public enum MenuTypeEnum implements BaseEnum {
    CLICK("click","按钮"),
    VIEW("view","网页链接"),
    MINIPROGRAM("miniprogram","小程序"),
    SCANCODE_PUSH("scancode_push","扫码事件"),
    SCANCODE_WAITMSG("scancode_waitmsg","扫码事件且弹出提示"),
    PIC_SYSPHOTO("pic_sysphoto","弹出系统拍照功能"),
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album","弹出系统拍照或相册"),
    PIC_WEIXIN("pic_weixin","弹出系统相册"),
    LOCATION_SELECT("location_select","弹出地理位置选择器"),
    MEDIA_ID("media_id","永久素材下发"),
    VIEW_LIMITED("view_limited","永久素材对应图文消息");
    private String code;
    private String message;
    MenuTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
