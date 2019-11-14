package com.bj.pro.recruit.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 企业信息
 */
@Entity //jpa 实现映射
@Table(name = "tb_enterprise")
@DynamicUpdate
@Data
public class Enterprise implements Serializable {
    @Id
    private String id;
    /**企业名称*/
    private String name;
    /** 企业简介 */
    private String summary;
    /** 企业地址 */
    private String address;
    /** 标签列表 逗号分隔 */
    private String labels;
    /** 企业位置坐标 (经度，维度) */
    private String coordinate;
    /** 是否热门    0：非热门，1：热门 */
    private String ishot;
    /** logo位置 */
    private String logo;
    /** 职位数 */
    private Long jobcounts;
    /** 网址 */
    private String url;
}
