package com.bj.stu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private int gender;
    private String telephone;
    private Date registerdate;
}
