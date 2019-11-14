/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

/**
 * 包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * 复杂按钮（父按钮
 * @author 白健
 */
@Data
public class ComplexButton extends Button {
	private Button[] sub_button;  
}
