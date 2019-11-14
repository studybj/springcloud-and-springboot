package com.bj.pro.user.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity //jpa 实现映射
@Table(name = "tb_user")
@DynamicUpdate
@Data
public class User implements Serializable {
    @Id
    private String id;
    /**昵称*/
    private String nickname;
    /**手机号码'*/
    private String mobile;
    /**密码*/
    private String password;
    /**性别*/
    private String sex;
    /**生日*/
    private Date birthday;
    /**头像*/
    private String avatar;
    /**email*/
    private String email;
    /**注册日期*/
    private Date regdate;
    /**修改日期*/
    private Date updatedate;
    /**最后登陆日期*/
    private Date lastdate;
    /**在线时长（分钟）*/
    private Long online;
    /**兴趣*/
    private String interest;
    /**个性*/
    private String personality;
    /**粉丝数*/
    private Integer fanscount;
    /**关注数*/
    private Integer followcount;
}
