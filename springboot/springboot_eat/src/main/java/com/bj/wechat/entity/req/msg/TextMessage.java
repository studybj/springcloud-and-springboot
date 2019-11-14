/**
 * 
 */
package com.bj.wechat.entity.req.msg;

import lombok.Data;

/**
 * 请求消息之文本消息
 * @author 白健
 */
@Data
public class TextMessage extends BaseMessage {
	//消息内容
	private String Context;
}
