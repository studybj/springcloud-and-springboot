package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 关键字 回复内容
 */
@Table(name = "wx_key_reply")
@Entity
@Data
public class KeyReply {
    @Id
    private String id;
    //是否首次关注回复
    private Integer isfirst;
    //关键字
    private String rkey;
    private String rtype;
    //状态，表示该条记录是否启用，对于相同关键字的记录只可有一条处于启用状态
    //默认为0,0表示禁用，1表示启用
    private Integer status;
    // 回复类型为文本，回复内容
    private String context;
    // 回复类型为图文，图文id列表
    private String articlelist;
    // 回复类型为图片，图片的url或者是media_id
    private String imageurl;
    // 回复类型为音频，音频的url或者是media_id
    private String voidceurl;
}
