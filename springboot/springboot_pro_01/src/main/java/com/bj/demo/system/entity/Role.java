package com.bj.demo.system.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private String id;
    private String name;    //角色名
    private int available;  //是否可用 0不可用，1可用
    private Date createDate;//创建时间

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
