package com.ssm.bean.mysql;

import java.util.Date;

public class Asker {
    private Integer askerid;

    private String askername;

    private String checkstate;

    private Date checkintime;

    private String changestate;

    private Integer weight;

    private String rolename;

    private String bakcontent;

    public Integer getAskerid() {
        return askerid;
    }

    public void setAskerid(Integer askerid) {
        this.askerid = askerid;
    }

    public String getAskername() {
        return askername;
    }

    public void setAskername(String askername) {
        this.askername = askername == null ? null : askername.trim();
    }

    public String getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(String checkstate) {
        this.checkstate = checkstate == null ? null : checkstate.trim();
    }

    public Date getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    public String getChangestate() {
        return changestate;
    }

    public void setChangestate(String changestate) {
        this.changestate = changestate == null ? null : changestate.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getBakcontent() {
        return bakcontent;
    }

    public void setBakcontent(String bakcontent) {
        this.bakcontent = bakcontent == null ? null : bakcontent.trim();
    }
}