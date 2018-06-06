package com.ssm.controller;

import com.ssm.bean.LoginResult;
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

    /**
     * 重置密码
     * @param id 被重置密码的用户ID
     * @return 返回给前端操作信息
     */
    @GetMapping(value = "/restPwd",name = "重置密码")
    public Page restPwd(Integer id){
        Integer i= userService.updatePwdByID(id);
        Page page = null;
        if(i>0){
            page = new Page(150,"密码重置成功");

        }else {
            page = new Page(151,"密码重置失败");

        }
        return page;
    }

    /**
     * 更新锁定状态
     * @param statue 1为没锁定，0为锁定
     * @return
     */
    @GetMapping(value = "updateLockStatue",name = "更新锁定状态")
    public Page updateLockStatue(Integer statue, Integer userid){
        Page page = null;
        if(statue ==1){
            userService.updateAddLock(statue,userid);
            page = new Page(160,"用户锁定成功");
        }

        if (statue==0){
            userService.updateDelLock(statue,userid);
            page = new Page(161,"用户解锁成功");
        }
        return page;
    }
    @PostMapping(value = "/getUserName",name = "查询数据库中已存在的用户名")
    public Page selectUserByName(String name){
        return userService.selectUserByName(name);
    }

    @PostMapping(value = "/add",name = "添加用户")
    public Page insertUser(String username,String password,String email){
        User user= new User();
        user.setLoginname(username);
        user.setPassword(password);
        user.setProtectemail(email);
        return userService.insertUser(user);
    }

    @PostMapping(value = "/addrole",name = "爲用戶添加角色")
    public Page insertUserAddRole(Integer userId,Integer roleId){
        return userService.insertUserAddRole(userId,roleId);
    }
}
