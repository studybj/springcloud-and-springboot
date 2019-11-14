/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

/**
 * 	没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。这类子菜单项一定会包含三个属性：type、name和key
 * 普通按钮（子按钮）
 * @author 白健
 */
@Data
public class CommonButton extends Button {
	private String type;  
    private String key;
}
