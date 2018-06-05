package com.ssm.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class Page<T> {
    private Integer code;
    private String msg;
    private List<T> data;
    private long count;

    public Page(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Page(PageInfo pageInfo, Integer code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = pageInfo.getList();
        this.count = pageInfo.getTotal();
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
