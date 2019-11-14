/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import lombok.Data;
import com.bj.wechat.entity.res.model.Music;
/**
 * 音乐信息
 * @author 白健
 */
@Data
public class MusicMessage extends BaseMessage {
	private Music Music;

}
