package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图文素材基础
 */
@Table(name = "wx_tw_material_base")
@Entity
@Data
public class TwMaterialBase {
    @Id
    //系统本身id 长度限制（12）
    private String id;
    //素材媒体文件上传后，获取标识
    private String media_id;
    //标题
    private String title;
    //是否显示封面，0为false，即不显示，1为true，即显示
    private Integer show_cover_pic;
    //图文消息的封面图片素材id（必须是永久mediaID）
    private String thumb_media_id;
    //作者
    private String author;
    //图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
    private String digest;
    //图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
    private String content;
    //图文消息的原文地址，即点击“阅读原文”后的URL
    private String content_source_url;
    //Uint32 是否打开评论，0不打开，1打开
    private Integer need_open_comment;
    //Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
    private Integer only_fans_can_comment;
    //创建时间
    private Date created_at;
    //状态，0已用，1已用
    private Integer status;
}
