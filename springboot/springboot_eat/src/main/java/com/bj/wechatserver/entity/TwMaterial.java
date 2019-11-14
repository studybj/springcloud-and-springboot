package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图文素材
 */
@Table(name = "wx_tw_material")
@Entity
@Data
public class TwMaterial {
    @Id
    //系统本身id
    private Integer id;
    private String media_id;
    private String material_ids;
    //状态 0禁用，1启用  默认0
    private Integer status;
    //创建时间
    private Date created_at;
}
