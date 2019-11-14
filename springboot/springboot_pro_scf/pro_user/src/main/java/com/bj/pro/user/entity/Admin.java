package com.bj.pro.user.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity //jpa 实现映射
@Table(name = "tb_admin")
@DynamicUpdate
@Data
public class Admin implements Serializable {
    @Id
    private String id;
    /**登陆名称*/
    private String loginname;
    /**状态 有效1，无效0，默认值为1*/
    private String state;
    /**密码*/
    private String password;
}
