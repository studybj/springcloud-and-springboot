/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import lombok.Data;
import com.bj.wechat.entity.res.model.Voice;

/**
 * 音乐信息
 * @author 白健
 */
@Data
public class VoiceMessage extends BaseMessage {
	private Voice Voice;

}
