package com.ssm.service;

import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public User selectUserByNameAndPassWord(String username, String password) {
        return userMapper.selectUserByNameAndPassWord(username,password);
    }
}
