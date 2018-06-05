package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Page;
import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    @Ann(msg = "用户登录系统，根据用户名和密码来查询该用户是否存在")
    public User selectUserByNameAndPassWord(String username, String password) {
        return userMapper.selectUserByNameAndPassWord(username,password);
    }

    public Page selectAllUser(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(userMapper.selectAllUser());
        Page page1 = new Page(pageInfo,0,"成功");
        return page1;
    }

    public Page deleteUserById(Integer userId) {
        int n = userMapper.deleteByPrimaryKey(userId);
        Page page = null;
        if(n>0){
            page = new Page(0,"删除成功");
        }else{
            page = new Page(1,"删除失败");
        }
        return page;
    }

    public Page updateUserById(Integer userId,String mail,String tel) {
        int n = userMapper.updateUserById(userId,mail,tel);
        Page page = null;
        if(n>0){
            page = new Page(0,"修改成功");
        }else{
            page = new Page(1,"修改失败");
        }
        return page;
    }
}
