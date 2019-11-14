package com.bj.wechat.entity.req.msg;

import lombok.Data;

/**
 * 请求消息之链接消息
 * @author 白健
 */
@Data
public class LinkMessage extends BaseMessage  {
	//消息标题
	private String Title;
	//消息描述
	private String Description;
	//消息链接
	private String Url;
}
