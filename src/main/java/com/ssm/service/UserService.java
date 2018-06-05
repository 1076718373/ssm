package com.ssm.service;

import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    @Ann(msg = "用户登录系统，根据用户名和密码来查询该用户是否存在")
    public User selectUserByNameAndPassWord(String username, String password) {
        return userMapper.selectUserByNameAndPassWord(username,password);
    }
}
