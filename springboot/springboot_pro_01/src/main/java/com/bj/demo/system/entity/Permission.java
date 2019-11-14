package com.bj.demo.system.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private String id;
    private String name;    //资源名称
    private String type;    //资源类型，包含menu(菜单)、button(按钮)、link(链接)等
    private String url;     //访问URL地址
    private String percode; //权限代码字符串，
    private String partentId;   //父节点id
    private String partentIds;  //父节点id列表串
    private String sortstring;   //排序号
    private int available;      //是否可用,1：可用，0不可用

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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPercode() {
        return percode;
    }
    public void setPercode(String percode) {
        this.percode = percode;
    }
    public String getPartentId() {
        return partentId;
    }
    public void setPartentId(String partentId) {
        this.partentId = partentId;
    }
    public String getPartentIds() {
        return partentIds;
    }
    public void setPartentIds(String partentIds) {
        this.partentIds = partentIds;
    }
    public String getSortstring() {
        return sortstring;
    }
    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }
}
