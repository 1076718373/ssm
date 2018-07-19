package com.ssm.service.mysql;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Role;
import com.ssm.dao.mysql.RoleMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Ann(msg = "根据用户id来查询到此用户具有的所有的角色id")
    public List<Integer> selectRoleIdByUserId(Integer id) {
        return roleMapper.selectRoleIdByUserId(id);
    }

    @Ann(msg = "通过用户id查询角色信息")
    public String selectRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Ann(msg = "查询所有角色的名称")
    public List<String> selectAllRoleName() {
        return roleMapper.selectAllRoleName();

    }

    @Ann(msg = "查询所有角色对应的id")
    public List<Integer> selectAllRoleId() {
        return roleMapper.selectAllRoleId();
    }


    @Ann(msg = "根据角色名查询某一角色操作")
    public List<Role> selectRoleByName(String name) {
        return roleMapper.selectRoleByName(name);
    }

    @Ann(msg = "根据某一模块的id查询拥有该模块的角色操作")
    public List<Role> selectRoleByModuleId(Integer moduleId) {
        return roleMapper.selectRoleByModuleId(moduleId);
    }

    @Ann(msg = "添加角色操作")
    public Integer insertRole(String name) {
        return roleMapper.insertRole(name);
    }

    @Ann(msg = "删除角色操作")
    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);

    }

    @Ann(msg = "修改角色操作")
    public Integer updateRoleById(Integer id, String roleName) {
        return roleMapper.updateRoleById(id, roleName);
    }


    @Ann(msg = "查询某一角色是否已具有指定的模块")
    public Integer selectRoleAndModuleById(Integer roleId, Integer moduleId) {
        return roleMapper.selectRoleAndModuleById(roleId, moduleId);
    }

    @Ann(msg = "查询所有的角色操作")
    public Page selectAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Page page1 = new Page(new PageInfo(roleMapper.selectAll()), 0, "ok");
        return page1;
    }

    @Ann(msg = "删除某一角色和模块的关系操作")
    public Integer deleteRoleAndModuleByRoleId(Integer id) {

        return roleMapper.deleteRoleAndModuleByRoleId(id);
    }

    @Ann(msg = "删除某一角色和权限的关系操作")
    public Integer deleteRoleAndPermissionByRoleId(Integer id) {
        return roleMapper.deleteRoleAndPermissionByRoleId(id);
    }

    @Ann(msg = "删除某一角色和用户的关系操作")
    public Integer deleteRoleAndUserByRoleId(Integer id) {
        return roleMapper.deleteRoleAndUserByRoleId(id);
    }

    @Ann(msg = "为角色分配模块的操作")
    public Integer InsertRoleAndModuleById(Integer roleId, Integer moduleId) {
        return roleMapper.InsertRoleAndModuleById(roleId, moduleId);
    }

    @Ann(msg = "删除角色的某一模块的操作")
    public Integer deleteModuleAndRoleByModuleId(Integer roleId, Integer moduleId) {
        return roleMapper.deleteModuleAndRoleByModuleId(roleId, moduleId);
    }


    @Ann(msg = "查询所有角色信息")
    public List<Role> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }

    @Ann(msg = "设置用户的角色")
    public Integer updateRoleName(String newRoleName, String oldRoleName) {
        return roleMapper.updateRoleName(newRoleName, oldRoleName);
    }
}
