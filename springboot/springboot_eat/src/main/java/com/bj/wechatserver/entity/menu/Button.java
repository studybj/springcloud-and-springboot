/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

/**
 * 菜单项的基类，所有一级菜单、二级菜单都共有一个相同的属性，那就是name
 * 按钮的基类
 * @author 白健
 */
@Data
public class Button {
	private String name;
}
