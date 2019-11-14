/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import com.bj.wechat.entity.res.model.Video;
import lombok.Data;

/**
 * 视频信息
 * @author 白健
 */
@Data
public class VideoMessage extends BaseMessage {
	private Video Video;

}
