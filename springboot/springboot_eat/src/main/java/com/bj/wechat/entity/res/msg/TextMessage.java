/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import lombok.Data;
/**
 * 响应消息指文本消息
 * @author 白健
 */
@Data
public class TextMessage extends BaseMessage {
	//回复的消息内容
	private String Content;

}
