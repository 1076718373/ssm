package com.ssm.bean;

public class Module {
    private Integer id;

    private String name;

    private Integer parentid;

    private String path;

    private Integer weight;

    private String ops;

    private Integer int0;

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

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops == null ? null : ops.trim();
    }

    public Integer getInt0() {
        return int0;
    }

    public void setInt0(Integer int0) {
        this.int0 = int0;
    }
}