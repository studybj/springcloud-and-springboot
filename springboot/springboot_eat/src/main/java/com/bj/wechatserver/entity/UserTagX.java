package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户与标签关联
 */
@Table(name = "wx_user_tag_x")
@Entity
@Data
public class UserTagX {
    @Id
    private String id;
    //用户id
    private String userid;
    //标签wxid
    private String tagid;
    //创建时间
    private Date createTime;
}
