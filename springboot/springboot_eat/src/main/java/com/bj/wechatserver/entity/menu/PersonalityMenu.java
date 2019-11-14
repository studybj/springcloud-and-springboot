package com.bj.wechatserver.entity.menu;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "wx_menu_match")
@Entity
@Data
public class PersonalityMenu extends Matchrule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    //微信公众号的个性化菜单id
    private String menuid;
    //系统菜单的id列表
    private String menuidlist;

    private Date create_time;
}
