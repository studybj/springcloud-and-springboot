/**
 * 
 */
package com.bj.wechat.entity.req.msg.event;

import lombok.Data;

/**
 * 请求消息之菜单点击事件跳转连接
 * @author 白健
 * event: view,view_miniprogram
 */
@Data
public class ViewOrMiniprogramMessage extends ClickMessage {
    //指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
    private String MenuID;
}
