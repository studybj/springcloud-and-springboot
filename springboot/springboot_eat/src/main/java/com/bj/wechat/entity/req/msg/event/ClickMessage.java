/**
 * 
 */
package com.bj.wechat.entity.req.msg.event;

import com.bj.wechat.entity.req.msg.BaseMessage;
import lombok.Data;

/**
 * 请求消息之菜单点击事件消息
 * @author 白健
 */
@Data
public class ClickMessage extends BaseMessage {
	//事件类型,
	private String Event;
	//事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;
}
