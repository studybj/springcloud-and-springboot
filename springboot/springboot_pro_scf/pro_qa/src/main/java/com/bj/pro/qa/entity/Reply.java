package com.bj.pro.qa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_reply")
@DynamicUpdate
@Data
public class Reply implements Serializable {
    @Id
    private String id;
    /** 问题id */
    private String problemid;
    /** 回复内容 */
    private String content;
    /** 回复时间 */
    private Date createtime;
    /** 更新时间 */
    private Date updatetime;
    /** 回复人ID */
    private String userid;
    /** 回复人昵称 */
    private String nickname;




}
