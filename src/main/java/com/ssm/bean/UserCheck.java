package com.ssm.bean;

import java.util.Date;

public class UserCheck {
    private Integer id;

    private String userid;

    private String username;

    private Date checkintime;

    private String checkstate;

    private String iscancel;

    private Date checkouttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    public String getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(String checkstate) {
        this.checkstate = checkstate == null ? null : checkstate.trim();
    }

    public String getIscancel() {
        return iscancel;
    }

    public void setIscancel(String iscancel) {
        this.iscancel = iscancel == null ? null : iscancel.trim();
    }

    public Date getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(Date checkouttime) {
        this.checkouttime = checkouttime;
    }
}