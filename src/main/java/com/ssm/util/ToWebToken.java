package com.ssm.util;

import java.util.List;

//返回给web端的token信息的pojo类
public class ToWebToken {
    private Integer id;
    private List<String> roles;//用户所具有的角色
    private List<String> permissions;//用户所具有的所有的权限


    public ToWebToken(Integer id, List<String> roles, List<String> permissions) {
        this.id = id;
        this.roles = roles;
        this.permissions = permissions;
    }
    public ToWebToken(Integer id,  List<String> permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public ToWebToken() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
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
