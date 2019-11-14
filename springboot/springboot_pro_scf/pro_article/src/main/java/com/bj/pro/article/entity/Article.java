package com.bj.pro.article.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息
 */
@Entity //jpa 实现映射
@Table(name = "tb_article")
@DynamicUpdate //该注解是对于更新等时，时间动态更新
@Data
public class Article implements Serializable {
    @Id
    private String id;
    /** 专栏id */
    private String columnid;
    /** 用户id */
    private String userid;
    /** 所属频道id 关联频道id*/
    private String channelid;
    /** 文章标题 */
    private String title;
    /** 文章内容 */
    private String content;
    /** 文章封面图片地址 */
    private String image;
    /** 发表日期 */
    private Date createtime;
    /** 更新日期 */
    private Date updatetime;
    /** 是否公开，默认为0  0：不公开，1：公开 */
    private String ispublic = "0";
    /** 是否置顶，默认为0  0：不置顶，1：置顶 */
    private String istop = "0";
    /** 浏览量 */
    private Integer visits = 0;
    /** 点赞数 */
    private Integer thumbup = 0;
    /** 评论数 */
    private Integer comment = 0;
    /** 审核状态    0：未审核，1：已审核通过, 2 审核未通过 */
    private String  state;
    /** 文章类型    0：分享，1：原创，2：专栏 */
    private String type;
    /** url地址 */
    private String url;
}
