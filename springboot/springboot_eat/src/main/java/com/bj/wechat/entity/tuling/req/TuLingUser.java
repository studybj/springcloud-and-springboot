package com.bj.wechat.entity.tuling.req;

import lombok.Data;

@Data
public class TuLingUser {
    //机器人标识 32位
    private String apiKey;
    //用户唯一标识 长度小于等于32位
    private String userId;
    //群聊唯一标识 长度小于等于64位
    private String groupId;
    //群内用户昵称 长度小于等于64位
    private String userIdName;
}
