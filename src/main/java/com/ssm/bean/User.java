package com.ssm.bean;

import java.util.Date;

public class User {
    private Integer id;

    private String loginname;

    private String password;

    private String islockout;

    private Date lastlogintime;

    private Integer psdwrongtime;

    private Date locktime;

    private String protectemail;

    private String protectmtel;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIslockout() {
        return islockout;
    }

    public void setIslockout(String islockout) {
        this.islockout = islockout == null ? null : islockout.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Integer getPsdwrongtime() {
        return psdwrongtime;
    }

    public void setPsdwrongtime(Integer psdwrongtime) {
        this.psdwrongtime = psdwrongtime;
    }

    public Date getLocktime() {
        return locktime;
    }

    public void setLocktime(Date locktime) {
        this.locktime = locktime;
    }

    public String getProtectemail() {
        return protectemail;
    }

    public void setProtectemail(String protectemail) {
        this.protectemail = protectemail == null ? null : protectemail.trim();
    }

    public String getProtectmtel() {
        return protectmtel;
    }

    public void setProtectmtel(String protectmtel) {
        this.protectmtel = protectmtel == null ? null : protectmtel.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}