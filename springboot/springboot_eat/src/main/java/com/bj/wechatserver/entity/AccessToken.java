package com.bj.wechatserver.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 微信通用接口凭证
 * @author 白健
 */
/*@Table(name = "wx_token")
@Entity*/
@Data
public class AccessToken {
    private String id;
    // 获取到的凭证
    private String token;
    // 凭证有效时间，单位：秒
    private int expiresIn;
    //用户id
    private String appid;
    //用户密钥
    private String appSecret;
    private Date createtime;
}
