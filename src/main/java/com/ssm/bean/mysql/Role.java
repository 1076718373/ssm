package com.ssm.bean.mysql;

public class Role {
    private Integer id;

    private String name;

    private Integer int0;

    private String string0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getInt0() {
        return int0;
    }

    public void setInt0(Integer int0) {
        this.int0 = int0;
    }

    public String getString0() {
        return string0;
    }

    public void setString0(String string0) {
        this.string0 = string0 == null ? null : string0.trim();
    }
}