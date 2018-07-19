package com.ssm.bean.mysql;

import java.util.Date;

public class SystemlogMessage {
    private Integer idsystemlogmessage;

    private String systemlogmessageName;

    private String systemlogmessageRoles;

    private String systemlogmessagMethod;

    private String systemlogmessageDescription;

    private String systemlogmessageParams;

    private Date systemlogmessageStarttime;

    private String systemlogmessageTime;

    private String systemlogmessageSuccessful;

    private String systemlogmessageException;

    public Integer getIdsystemlogmessage() {
        return idsystemlogmessage;
    }

    public void setIdsystemlogmessage(Integer idsystemlogmessage) {
        this.idsystemlogmessage = idsystemlogmessage;
    }

    public String getSystemlogmessageName() {
        return systemlogmessageName;
    }

    public void setSystemlogmessageName(String systemlogmessageName) {
        this.systemlogmessageName = systemlogmessageName == null ? null : systemlogmessageName.trim();
    }

    public String getSystemlogmessageRoles() {
        return systemlogmessageRoles;
    }

    public void setSystemlogmessageRoles(String systemlogmessageRoles) {
        this.systemlogmessageRoles = systemlogmessageRoles == null ? null : systemlogmessageRoles.trim();
    }

    public String getSystemlogmessagMethod() {
        return systemlogmessagMethod;
    }

    public void setSystemlogmessagMethod(String systemlogmessagMethod) {
        this.systemlogmessagMethod = systemlogmessagMethod == null ? null : systemlogmessagMethod.trim();
    }

    public String getSystemlogmessageDescription() {
        return systemlogmessageDescription;
    }

    public void setSystemlogmessageDescription(String systemlogmessageDescription) {
        this.systemlogmessageDescription = systemlogmessageDescription == null ? null : systemlogmessageDescription.trim();
    }

    public String getSystemlogmessageParams() {
        return systemlogmessageParams;
    }

    public void setSystemlogmessageParams(String systemlogmessageParams) {
        this.systemlogmessageParams = systemlogmessageParams == null ? null : systemlogmessageParams.trim();
    }

    public Date getSystemlogmessageStarttime() {
        return systemlogmessageStarttime;
    }

    public void setSystemlogmessageStarttime(Date systemlogmessageStarttime) {
        this.systemlogmessageStarttime = systemlogmessageStarttime;
    }

    public String getSystemlogmessageTime() {
        return systemlogmessageTime;
    }

    public void setSystemlogmessageTime(String systemlogmessageTime) {
        this.systemlogmessageTime = systemlogmessageTime == null ? null : systemlogmessageTime.trim();
    }

    public String getSystemlogmessageSuccessful() {
        return systemlogmessageSuccessful;
    }

    public void setSystemlogmessageSuccessful(String systemlogmessageSuccessful) {
        this.systemlogmessageSuccessful = systemlogmessageSuccessful == null ? null : systemlogmessageSuccessful.trim();
    }

    public String getSystemlogmessageException() {
        return systemlogmessageException;
    }

    public void setSystemlogmessageException(String systemlogmessageException) {
        this.systemlogmessageException = systemlogmessageException == null ? null : systemlogmessageException.trim();
    }
}