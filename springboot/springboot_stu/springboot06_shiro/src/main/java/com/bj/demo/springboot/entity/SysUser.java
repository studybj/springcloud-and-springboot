package com.bj.demo.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
  private String id;
  private String usercode;
  private String username;
  private String password;
}
