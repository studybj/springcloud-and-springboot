/**
 * 
 */
package com.bj.wechat.entity.req.msg;

import lombok.Data;

/**
 * 请求消息之语音信息
 * @author 白健
 */
@Data
public class VoiceMessage extends BaseMessage {
	//媒体ID
	private String MediaId;
	//语音格式 amr
	private String Fomcat;
	//语音识别结果，UTF8编码
	private String Recognition;
}
