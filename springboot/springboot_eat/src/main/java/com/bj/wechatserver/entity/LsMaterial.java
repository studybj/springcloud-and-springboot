package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 临时素材管理
 */
@Table(name = "wx_ls_material")
@Entity
@Data
public class LsMaterial {
    @Id
    //系统本身id
    private String id;
    //素材类型  图片（image），语音（voice）视频（video）缩略图（thumb）
    private String type;
    //素材媒体文件上传后，获取标识
    private String media_id;
    //系统路径
    private String localUrl;
    //媒体文件上传时间戳
    private String created_at;
    //状态
    private Integer status;

}
