/**
 * 
 */
package com.bj.wechat.entity.req.msg.event;

import lombok.Data;

/**
 * 请求消息之菜单点击事件->扫码推事件的事件推送,扫码推事件且弹出“消息接收中”提示框的事件推送
 * @author 白健
 * event:scancode_push,scancode_waitmsg
 */
@Data
public class ScancodePushOrWaitmsgMessage extends ClickMessage {
    //扫描信息
    private String ScanCodeInfo;
    //扫描类型，一般是qrcode
    private String ScanType;
    //扫描结果，即二维码对应的字符串信息
    private String ScanResult;
}
