package com.bj.pro.spit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 吐槽信息 该表连接MongoDB数据库
 */
@Data
public class Spit implements Serializable {
    @Id
    private String _id;
    /** 发布人id */
    private String userid;
    /** 上级id */
    private String parentid;
    /** 吐槽内容 */
    private String content;
    /** 发布日期 */
    private Date publishtime;
    /** 发布人昵称 */
    private String nickname;
    /** 浏览量 */
    private Integer visits = 0;
    /** 点赞数 */
    private Integer thumbup = 0;
    /** 分享数 */
    private Integer share = 0;
    /** 回复数 */
    private Integer comment = 0;
    /** 是否可见，默认为0    0：可见，1：不可见 */
    private String  state;
}
