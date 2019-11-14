package com.bj.wechat.entity.req.msg;
import lombok.Data;
/**
 * 请求消息之图片信息
 * @author 白健
 */
@Data
public class ImageMessage extends BaseMessage  {
	//图片链接
	private String PicUrl;
	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	private String MediaId;
}
