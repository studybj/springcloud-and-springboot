/**
 * 
 */
package com.bj.wechatserver.entity.menu;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 白健
 */
@Table(name = "wx_menu")
@Entity
@Data
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//父菜单id
	private Integer pid;
	//排序
	private Integer sort;
	//状态
	private Integer status;
	//按钮(菜单)类型
	private String type;
	//按钮(菜单)名称
	private String name;
	//按钮(菜单)key
	@Column(name = "mkey")
	private String key;
	//按钮(菜单)路径
	private String url;
	//按钮若为小程序，所需appid
	private String appid;
	//按钮若为小程序，所需路径
	private String pagepath;
	//按钮(菜单)路径
	private String media_id;

	private Date createTime;
	//是否生成普通菜单，0否，1是，默认0
	private Integer	isordinary;
	//是否生成个性化菜单，0否，1是，默认0
	private Integer	ispersonality;
}
