package com.bj.pro.friend.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 好友信息
 */
@Entity //jpa 实现映射
@Table(name = "tb_friend")
@DynamicUpdate //该注解是对于更新等时，时间动态更新
@Data
@IdClass(Friend.class)
public class Friend implements Serializable {
    /** 用户id */
    @Id
    private String userid;
    /** 好友id */
    @Id
    private String friendid;
    /** 是否互相喜欢，0非好友，1单向喜欢，2互相喜欢 */
    private String islike;
}
