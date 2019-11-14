package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户标签
 */
@Table(name = "wx_user_tag")
@Entity
@Data
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //最多100条启用状态下
    //系统本身id
    private Integer id;
    //标签id
    private String wxid;
    //标签名
    private String name;
    //此标签下粉丝数
    private Integer count;
    //状态，
    //默认为0,0表示禁用，1表示启用
    private Integer status;
    //创建时间
    private Date createTime;
}
