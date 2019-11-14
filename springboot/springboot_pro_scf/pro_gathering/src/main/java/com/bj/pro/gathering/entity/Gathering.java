package com.bj.pro.gathering.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动信息
 */
@Entity //jpa 实现映射
@Table(name = "tb_gathering")
@DynamicUpdate //该注解是对于更新等时，时间动态更新
@Data
public class Gathering implements Serializable {
    @Id
    private String id;
    /** 活动名称 */
    private String name;
    /** 活动简介 */
    private String summary;
    /** 活动详情 */
    private String detail;
    /** 组织者 */
    private String sponsor;
    /** image图片 */
    private String image;
    /** 活动开始时间 */
    private Date starttime;
    /** 活动结束时间 */
    private Date endtime;
    /** 活动地址 */
    private String address;
    /** 状态    0：，1： */
    private String  state;
    /**  */
    private String city;
    /**  */
    private Date enrolltime;
}
