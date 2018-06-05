package com.ssm.controller;

import com.ssm.bean.Page;
import com.ssm.bean.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user",name = "用户模块")
public class UserContriller {
    @Autowired
    UserService userService;
    @GetMapping(value = "/allUser",name = "查询所有用户")
    public Page selectAllUser(Integer page, Integer limit){
        return userService.selectAllUser(page,limit);
    }
    @PostMapping(value = "/deleteUser",name = "通过id删除用户")
    public Page deleteUserById(Integer userId){
        return userService.deleteUserById(userId);
    }

    @PostMapping(value = "/updateUser",name = "通过id修改用户信息")
    public Page updateUserById(Integer userId,String mail,String tel){
        return userService.updateUserById(userId,mail,tel);
    }
}
