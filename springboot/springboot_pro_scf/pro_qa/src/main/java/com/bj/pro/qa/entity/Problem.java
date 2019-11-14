package com.bj.pro.qa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 问答模块-问题类
 */
@Entity
@Table(name = "tb_problem")
@DynamicUpdate
@Data
public class Problem implements Serializable {
    @Id
    private String id;
    /** 问题标题 */
    private String title;
    /** 问题内容 */
    private String content;
    /** 发布日期 */
    private Date createtime;
    /** 更新日期 */
    private Date updatetime;
    /** 发布人ID */
    private String userid;
    /** 发布人昵称 */
    private String nickname;
    /** 浏览量 */
    private Integer visits;
    /** 点赞数 */
    private Integer thumbup;
    /** 回复数 */
    private Integer reply;
    /** 是否解决 */
    private String solve;
    /** 最新回复人 */
    private String replyname;
    /** 最新回复时间 */
    private Date replytime;
}
