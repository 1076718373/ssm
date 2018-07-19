package com.ssm.service.mysql;

import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.User;
import com.ssm.dao.mysql.UserMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int udateUserIPAndTimeByUsername(String username, Date date, String remoteHost) {
        return userMapper.udateUserIPAndTimeByUsername(username, date, remoteHost);
    }


    @Ann(msg = "用户登录系统，根据用户名和密码来查询该用户是否存在")
    public User selectUserByNameAndPassWord(String username, String password) {
        return userMapper.selectUserByNameAndPassWord(username, password);
    }

    @Ann(msg = "查看所有用户列表")
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Ann(msg = "通过id删除用户")
    public Page deleteUserById(Integer userId) {
        int m = userMapper.deleteUserRoleByUserId(userId);
        int n = userMapper.deleteByPrimaryKey(userId);
        Page page = null;
        if (n > 0) {
            page = new Page(0, "删除成功");
        } else {
            page = new Page(1, "删除失败");
        }
        return page;
    }

    @Ann(msg = "通过id修改用户信息")
    public Page updateUserById(Integer userId, String mail, String tel) {
        int n = userMapper.updateUserById(userId, mail, tel);
        Page page = null;
        if (n > 0) {
            page = new Page(0, "修改成功");
        } else {
            page = new Page(1, "修改失败");
        }
        return page;
    }

    @Ann(msg = "通过id重置密码为ysd123")
    public Integer updatePwdByID(Integer id) {
        System.out.println(id);
        return userMapper.updatePwdByID(id);
    }

    @Ann(msg = "用户锁定")
    public boolean updateAddLock(Integer statue, Integer userid) {
        return userMapper.updateAddLock(statue, userid);
    }

    @Ann(msg = "用户解锁")
    public boolean updateDelLock(Integer statue, Integer userid) {
        return userMapper.updateDelLock(statue, userid);
    }

    @Ann(msg = "检索用户信息")
    public List<User> selectUserByNameOrLock(String name, String lock) {
        return userMapper.selectUserByNameOrLock(name, lock);
    }

    @Ann(msg = "修改用户的角色")
    public int insertRoleByUserId(Integer userId, Integer roleId) {
        return userMapper.insertRoleByUserId(userId, roleId);
    }

    @Ann(msg = "添加用户")
    public int insertAddUser(String addName, String addPwd, String addEmail, String addPhone) {
        return userMapper.insertAddUser(addName, addPwd, addEmail, addPhone);
    }

    public int updateLoginTimeByUsername(String username) {
        return userMapper.updateLoginTimeByUsername(username);
    }

    public int insertUser(List<User> users) {
        int n = 0;
        for (User user : users) {
            n = userMapper.insertSelective(user);
        }
        return n;
    }
}
