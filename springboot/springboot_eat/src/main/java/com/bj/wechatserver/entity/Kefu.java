package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 客服管理
 */
@Table(name = "wx_kefu")
@Entity
@Data
public class Kefu {
    @Id
    //系统本身id
    private String id;
    //客服工号
    private String kf_id;
    //客服账号,格式为：账号前缀@公众号微信号
    private String kf_account;
    //客服昵称
    private String kf_nick;
    //客服昵称，最长6个汉字或12个英文字符
    private String nickname;
    //客服密码
    private String password;
    //头像
    private String kf_headimgurl;
    //和客服头像相关
    private String media;
    //客服状态
    private Integer status;

    private Date create_time;
}
