package com.bj.stu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private long id;
    private String username;
    private String password;
}
