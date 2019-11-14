/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

/**
 * model
 * @author 白健
 */
@Data
public class MiniProgramButton extends CommonButton {
    private String appid;
    private String pagepath;
}
