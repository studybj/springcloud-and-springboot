package com.bj.pro.base.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity //jpa 实现映射
@Table(name = "tb_label")
@DynamicUpdate
@Data
public class Label implements Serializable {
    @Id
    private String id;
    /**标签名称*/
    private String name;
    /**标签状态 有效1，无效0，默认值为1*/
    private String state;
    /**使用数量*/
    private Long count;
    /**关注数*/
    private Long fans;
    /**是否推荐 推荐1，不推荐0，默认不推荐0*/
    private String recommend;
}
