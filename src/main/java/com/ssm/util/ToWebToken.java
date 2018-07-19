package com.ssm.util;

import java.util.List;

//返回给web端的token信息的pojo类
public class ToWebToken {
    private Integer id;
    private String roles;//用户所具有的角色
    private List<String> permissions;//用户所具有的所有的权限
    private String name;

    public String getName() {
        return name;
    }

    public ToWebToken(Integer id, String roles, List<String> permissions, String name) {
        this.id = id;
        this.roles = roles;
        this.permissions = permissions;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToWebToken(Integer id, List<String> permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public ToWebToken(Integer id, String roles, List<String> permissions) {
        this.id = id;
        this.roles = roles;
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "ToWebToken{" +
                "id=" + id +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
