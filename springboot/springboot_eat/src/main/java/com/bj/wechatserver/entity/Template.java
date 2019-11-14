package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 模板消息
 */
@Table(name = "wx_template")
@Entity
@Data
public class Template {
    @Id
    private String id;
    private Integer status;
}
