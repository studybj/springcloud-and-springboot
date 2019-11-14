/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

/**
 * 菜单对象包含多个菜单项（最多只能有3个），这些菜单项即可以是子菜单项（不
 * 含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 * @author 白健
 */
@Data
public class MenuInfo {
	private Button[] button;
	private Matchrule matchrule ;//个性化菜单-	菜单匹配规则

}
