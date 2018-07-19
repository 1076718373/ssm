package com.ssm.bean.mysql;

import org.omg.PortableInterceptor.Interceptor;

import java.util.List;

public class LoginResult {
    private Integer code;
    private String msg;
    private Integer userId;
    private String userName;
    private List<Integer> roles;
    private List<Integer> modules;
    private String token;


    @Override
    public String toString() {
        return "LoginResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                ", modules=" + modules +
                ", token='" + token + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public LoginResult() {
    }

    public LoginResult(Integer code, String msg, Integer userId, String userName, List<Integer> roles, List<Integer> modules, String token) {
        this.code = code;
        this.msg = msg;
        this.userId = userId;
        this.userName = userName;
        this.roles = roles;
        this.modules = modules;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<Integer> getModules() {
        return modules;
    }

    public void setModules(List<Integer> modules) {
        this.modules = modules;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
