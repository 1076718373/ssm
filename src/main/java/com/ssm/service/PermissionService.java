package com.ssm.service;

import com.ssm.bean.Permission;
import com.ssm.dao.PermissionMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Ann(msg = "查询数据库中已存在的权限地址")
    public List<String> selectURL() {
        return permissionMapper.selectURL();
    }

    public Integer insertPermissions(List<Permission> newPermission) {
       return permissionMapper.insertPermissions(newPermission);
    }
}
