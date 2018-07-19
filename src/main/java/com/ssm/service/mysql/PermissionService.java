package com.ssm.service.mysql;

import com.ssm.bean.mysql.Permission;
import com.ssm.dao.mysql.PermissionMapper;
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

    @Ann(msg = "向数据库更新权限信息")
    public Integer insertPermissions(List<Permission> newPermission) {
        return permissionMapper.insertPermissions(newPermission);
    }

    @Ann(msg = "通过用户id查找用户所有权限")
    public List<String> selectPermissionByUserId(Integer userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }

    @Ann(msg = "查询某一角色所拥有的权限的操作")
    public List<Permission> selectRolePermissions(Integer id) {
        return permissionMapper.selectRolePermissions(id);
    }

    @Ann(msg = "查询某一角色没有的权限的操作")
    public List<Permission> selectRoleNoPermissions(Integer id) {
        return permissionMapper.selectRoleNoPermissions(id);
    }

    @Ann(msg = "为角色添加权限的操作")
    public Integer insertPermissionAndRole(Integer roleId, Integer perId) {
        return permissionMapper.insertPermissionAndRole(roleId, perId);
    }

    @Ann(msg = "为角色删除权限的操作")
    public Integer deletePermissionAndRole(Integer roleId, Integer perId) {
        return permissionMapper.deletePermissionAndRole(roleId, perId);
    }
}
