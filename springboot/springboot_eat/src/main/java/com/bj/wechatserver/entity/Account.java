package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 账号管理，生成带参数的二维码
 */
@Table(name = "wx_account")
@Entity
@Data
public class Account {
    @Id
    //id
    private String id;
    //二维码类型 1临时，2永久
    private Integer type;
    //该二维码有效时间，以秒为单位。若类型为2，则为空
    // 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
    private Integer expire_seconds;
    //二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
    private String action_name;
    //二维码详细信息
    private String action_info;
    //场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
    private Integer scene_id;
    //场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
    private String scene_str;
    //获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
    private String ticket;
    //二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
    private String url;
    //源图片本地存放地址
    private String source_img_url;
    //二维码图片本地存放地址
    private String local_url;
    //最终二维码图片本地存放地址
    private String result_url;
    //状态  0禁用，1启用   默认0
    private Integer status;
    private Date create_time;
}
