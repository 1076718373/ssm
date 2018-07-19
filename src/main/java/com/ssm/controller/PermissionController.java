package com.ssm.controller;

import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Permission;
import com.ssm.service.mysql.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@RequestMapping(value = "/permission", name = "系统模块")
@CrossOrigin
@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 向数据库更新权限信息
     *
     * @return
     */
    @GetMapping(value = "/update", name = "更新权限信息")
    public Object insertPermissions() {
        Integer i = permissionService.insertPermissions(getNewPermission());
        return "新增了" + i + "个权限";
    }

    /**
     * 得到所有新增还未加入数据的权限
     *
     * @return
     */
    private synchronized List<Permission> getNewPermission() {
        List<String> oldPermissionURL = permissionService.selectURL();
        List<Permission> resultList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map =
                requestMappingHandlerMapping.getHandlerMethods();

        Collection<HandlerMethod> handlerMethods = map.values();
        if (handlerMethods == null || handlerMethods.isEmpty()) {
            return null;
        }

        //遍历所有方法
        for (HandlerMethod m : handlerMethods) {
            RequestMapping methodMapping = m.getMethodAnnotation(RequestMapping.class);
            String mappingName = methodMapping.name();
            if (!mappingName.equals("")) {
                Permission p = new Permission();
                String methodValue = methodMapping.value()[0];
                RequestMapping classMapping = m.getBeanType().getAnnotation(RequestMapping.class);
                String className = classMapping.name();
                String classValue = classMapping.value()[0];
                String permissionValue = (classValue + ":" + methodValue).replace("/", "");
                if (oldPermissionURL.size() > 0) {
                    if (!oldPermissionURL.contains(permissionValue)) {
                        p.setPermissionmodule(className);
                        p.setPermissionvalue(permissionValue);
                        p.setPermissionname(mappingName);
                        resultList.add(p);
                    }
                } else {
                    p.setPermissionmodule(className);
                    p.setPermissionvalue(permissionValue);
                    p.setPermissionname(mappingName);
                    resultList.add(p);
                }

            }
        }


        return resultList;
    }

    @GetMapping(value = "/selectper", name = "查询某一角色已有的权限的权限")
    public Object selectRolePermissions(Integer id) {
        return permissionService.selectRolePermissions(id);
    }

    @GetMapping(value = "/selectnoper", name = "查询某一角色没有的权限的权限")
    public Object selectRoleNoPermissions(Integer id) {
        return permissionService.selectRoleNoPermissions(id);
    }

    @PostMapping(value = "/addper", name = "为角色添加指定权限的权限")
    public Object insertPermissionAndRole(Integer roleId, Integer perId) {
        Integer i = permissionService.insertPermissionAndRole(roleId, perId);
        if (i > 0) {
            Page page = new Page(0, "ok");
            return page;
        }
        Page page = new Page(1, "no");
        return page;
    }

    @PostMapping(value = "/deleteper", name = "为角色删除权限的权限")
    public Object deletePermissionAndRole(Integer roleId, Integer perId) {
        Integer i = permissionService.deletePermissionAndRole(roleId, perId);
        if (i > 0) {
            Page page = new Page(0, "ok");
            return page;
        }
        Page page = new Page(1, "no");
        return page;
    }

}
