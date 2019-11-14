/**
 * 
 */
package com.bj.wechat.entity.req.msg;

import lombok.Data;

/**
 * 请求消息之地理位置信息
 * @author 白健
 */
@Data
public class LocationMessage extends BaseMessage {
	//地理位置纬度
	private String Location_X;
	//地理位置经度
	private String Location_Y;
	//地理位置缩放大小
	private String Scale;
	//地理位置信息
	private String Label;
}
