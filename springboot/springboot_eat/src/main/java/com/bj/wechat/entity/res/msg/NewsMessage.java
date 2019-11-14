/**
 * 
 */
package com.bj.wechat.entity.res.msg;

import com.bj.wechat.entity.res.model.Article;
import lombok.Data;

import java.util.List;

/**
 * 图文消息
 * @author 白健
 */
@Data
public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为 10 条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个 item 为大图
	private List<Article> Articles;
}
