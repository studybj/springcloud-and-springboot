package com.bj.wechat.entity.res.msg;

import lombok.Data;
import com.bj.wechat.entity.res.model.Image;
/**
 * 图片信息@author 白健
 */
@Data
public class ImageMessage extends BaseMessage {
	//图片
	private Image Image;
}
