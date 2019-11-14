package com.bj.demo.system.entity;

import java.io.Serializable;

public class RolePermission implements Serializable {
    private String id;
    private String roleId;
    private String promissionId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getPromissionId() {
        return promissionId;
    }
    public void setPromissionId(String promissionId) {
        this.promissionId = promissionId;
    }
}
