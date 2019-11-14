package com.bj.wechat.entity.req.msg;

import lombok.Data;

/**
 * 请求消息之视频信息或者小视频
 * @author 白健
 */
@Data
public class VideoMessage extends BaseMessage {
	//媒体ID 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;
}
