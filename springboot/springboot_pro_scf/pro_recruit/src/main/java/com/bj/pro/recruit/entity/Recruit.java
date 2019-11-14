package com.bj.pro.recruit.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 招聘信息
 */
@Entity //jpa 实现映射
@Table(name = "tb_recruit")
@DynamicUpdate
@Data
public class Recruit implements Serializable {
    @Id
    private String id;
    /** 招聘职位名称 */
    private String jobname;
    /** 薪资范围 */
    private String salary;
    /** 经验要求 */
    private String conditions;
    /** 学历要求 */
    private String education;
    /** 任职方式 */
    private String type;
    /** 办公地址 */
    private String address;
    /** 标签 */
    private String label;
    /** 职位描述 */
    private String content;
    /** 职位要求 */
    private String demand;
    /** 原网址 */
    private String url;
    /** 状态    0：关闭，1：开启，2：推荐 */
    private String state;
    /** 发布日期 */
    private Date createtime;
    /** 企业id */
    private String eid;
}
