package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.mysql.Module;
import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Role;
import com.ssm.service.mysql.ModuleService;
import com.ssm.service.mysql.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/role", name = "角色管理模块")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;


    /**
     * 查询所有的角色信息
     *
     * @return
     */
    @GetMapping(value = "/all", name = "查询所有的的角色")
    public Page selectAll(Integer page, Integer limit) {
        return roleService.selectAll(page, limit);
    }


    /**
     * 根据角色名查询某一角色的方法
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/forname", name = "根据角色名查询某一角色权限")
    public Page selectRoleByName(String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        Page page1 = new Page(new PageInfo(roleService.selectRoleByName(name)), 0, "ok");

        return page1;
    }

    /**
     * 根据模块id查询某些角色的方法
     *
     * @param
     * @return
     */
    @GetMapping(value = "/formodule", name = "根据模块查询角色拥有此模块的权限")
    public Page selectRoleByModuleId(Integer moduleId) {
        Page page = null;
        List<Role> roles = roleService.selectRoleByModuleId(moduleId);
        if (roles.size() <= 0 || roles == null) {
            page = new Page(1, "未查询到角色");
            return page;
        }
        page = new Page(0, "查询到了拥有该模块的角色");
        page.setObj(roles);
        return page;
    }

    /**
     * 添加角色的方法
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/add", name = "添加角色权限")
    public Page insertRole(String name) {
        Page page = null;
        Integer i = roleService.insertRole(name);
        if (i <= 0) {
            page = new Page(1, "添加角色失败");
            return page;
        }
        page = new Page(0, "添加角色成功");
        return page;
    }


    /**
     * 根据角色的id对某一角色删除
     *
     * @param id
     * @return
     */
    @PostMapping(value = "delete", name = "删除角色权限")
    public Page deleteRoleById(Integer id) {
        roleService.deleteRoleAndModuleByRoleId(id);
        roleService.deleteRoleAndPermissionByRoleId(id);
        roleService.deleteRoleAndUserByRoleId(id);

        Page page = null;
        Integer i = roleService.deleteRoleById(id);
        if (i <= 0) {
            page = new Page(1, "删除角色失败");
            return page;
        }
        page = new Page(0, "删除角色成功");
        return page;
    }

    /**
     * 根据角色id，对角色进行修改，其实也就修改了角色名
     *
     * @param id
     * @param roleName
     * @return
     */
    @PostMapping(value = "/set", name = "修改角色信息的权限")
    public Page updateRoleById(Integer id, String roleName) {
        Page page = null;
        Integer i = roleService.updateRoleById(id, roleName);
        if (i <= 0) {
            page = new Page(1, "修改角色信息失败");
            return page;
        }
        page = new Page(0, "修改角色信息成功");
        return page;
    }

    /**
     * 给角色分配模块的方法
     *
     * @param roleId
     * @param moduleId
     * @return
     */
    @PostMapping(value = "/setmodule", name = "给角色分配模块的权限")
    public Page updateRoleAndModuleById(Integer roleId, Integer moduleId) {
        Page page = null;
        Integer n = roleService.selectRoleAndModuleById(roleId, moduleId);
        if (n > 0) {
            page = new Page(1, "该角色已具有此模块");
            return page;
        }
        Integer i = roleService.InsertRoleAndModuleById(roleId, moduleId);
        if (i <= 0) {
            page = new Page(1, "为角色分配模块失败");
            return page;
        }
        page = new Page(0, "为角色分配模块成功");
        return page;
    }


    @GetMapping(value = "/selectNoModule", name = "查询角色不具有的模块的权限")
    public List<Module> selectNoModule(Integer id) {
        return moduleService.selectNoModule(id);
    }

    @GetMapping(value = "/selectModule", name = "查询角色已有的模块的权限")
    public List<Module> selectModule(Integer id) {
        return moduleService.selectModule(id);
    }


    @PostMapping(value = "/delModule", name = "删除角色的某一模块的权限")
    public Page deleteModuleAndRoleByModuleId(Integer roleId, Integer moduleId) {
        Integer i = roleService.deleteModuleAndRoleByModuleId(roleId, moduleId);
        if (i > 0) {
            Page page = new Page(0, "删除模块成功");
            return page;
        }
        Page page = new Page(1, "删除模块失败");
        return page;
    }


    /**
     * 查询所有角色
     *
     * @return
     */
    @PostMapping(value = "/queryRloesName", name = "查询全部角色")
    public List<Role> queryRolesName() {
        List<Role> roles = roleService.queryAllRoles();
        return roles;
    }

    /**
     * 给用户设置角色
     *
     * @param newRoleName 给用户加的角色名
     * @param oldRoleName 用户名
     * @return
     */
    @GetMapping(value = "/updateRoleName", name = "设置角色")
    public Page changeRoleName(String newRoleName, String oldRoleName) {
        Integer i = roleService.updateRoleName(newRoleName, oldRoleName);
        Page page = null;
        if (i > 0) {
            page = new Page(0, "角色设置成功");
        } else {
            page = new Page(4, "角色设置失败");
        }
        return page;
    }


}
