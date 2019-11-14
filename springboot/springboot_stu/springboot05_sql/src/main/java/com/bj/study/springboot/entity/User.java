package com.bj.study.springboot.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String usercode;
    private String username;
    private String password;
    private String salt;
    private String locked;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getLocked() {
        return locked;
    }
    public void setLocked(String locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked='" + locked + '\'' +
                '}';
    }
}
