/**
 * 
 */
package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图文model
 * @author 白健
 */
@Table(name = "wx_article_info")
@Entity
@Data
public class ArticleInfo {
	@Id
	private String id;
	// 图文消息名称
	private String title;
	// 图文消息描述
	private String description;
	// 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，
	//小图 80*80，限制图片 链接的域名需要与开发者填写的基本资料中的 Url 一致
	private String picUrl;
	// 点击图文消息跳转链接
	private String url;
}
