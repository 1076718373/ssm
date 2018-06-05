package com.ssm.controller;

import com.ssm.bean.LoginResult;
import com.ssm.bean.User;
import com.ssm.service.ModuleService;
import com.ssm.service.PermissionService;
import com.ssm.service.RoleService;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user",name = "用户登录模块")
public class LoginContriller {
    @Autowired
    private UserService userSerivice;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ModuleService moduleService;

    @PostMapping("/login")
    public LoginResult login(String username,String password){
        User user=userSerivice.selectUserByNameAndPassWord(username,password);
        LoginResult loginResult= new LoginResult();
        if(user==null){
            loginResult.setCode(104);
            loginResult.setMsg("用户不存在");
            return loginResult;
        }
        loginResult.setUserId(user.getId());
        loginResult.setUserName(user.getLoginname());
        loginResult.setMsg("登录成功");
        loginResult.setCode(100);
        List<Integer> rolesId  = roleService.selectRoleIdByUserId(user.getId());
        loginResult.setRoles(rolesId);
        loginResult.setModules(moduleService.selectModuleIdByRolesId(rolesId));
        return loginResult;
    }
}
