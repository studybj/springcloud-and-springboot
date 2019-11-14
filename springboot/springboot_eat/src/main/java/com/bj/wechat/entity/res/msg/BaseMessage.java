/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import lombok.Data;

/**
 * 消息基类(公众账号->普通用户)
 * @author 白健
 */
@Data
public class BaseMessage {
	//接收方账号(一个OpenId)
	private  String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间 (整型)
	private long CreateTime;
	//消息类型(text/music/news)
	private String MsgType;
	//位0x0001被标识时，星标刚收到的消息
	private int FuncFlag;
}
