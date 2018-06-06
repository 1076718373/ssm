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

    /**
     * 根据用户名来查询用户，如果用户存在，则不使用该用户名
     *
     * @param name
     * @return
     */
    @Ann(msg = "根据用户名来查询用户")
    public Page selectUserByName(String name) {
        User user = userMapper.selectUserByName(name);
        Page page = null;
        if (user == null) {
            page = new Page(0, "可以使用");
        } else {
            page = new Page(1, "已存在");
        }
        return page;
    }


    /**
     * 添加用戶的方法
     * @param user
     * @return
     */
    @Ann(msg = "添加用戶")
    public Page insertUser(User user) {
        Integer i = userMapper.insertSelective(user);
        Page page = null;
        if (i > 0) {
            page = new Page(0, "添加成功");
        } else {
            page = new Page(1, "添加失败");
        }
        return page;
    }

    /**
     * 為用戶添加新的角色
     * @param userId
     * @param roleId
     * @return
     */
    @Ann(msg = "為用戶添加新的角色")
    public Page insertUserAddRole(Integer userId, Integer roleId) {
        Integer i=userMapper.selectUserAddRole(userId,roleId);


        Page page = null;
        if (i > 0) {
            page = new Page(3, "id為"+userId+"的用戶已已擁有該角色");
            return page;
        }

        Integer n = userMapper.insertUserAddRole(userId,roleId);
        if (n > 0) {
            page = new Page(0, "添加新角色成功");
        } else {
            page = new Page(1, "添加新角色失败");
        }

        return page;
    }
    @Ann(msg = "用户登录系统，根据用户名和密码来查询该用户是否存在")
    public User selectUserByNameAndPassWord(String username, String password) {
        return userMapper.selectUserByNameAndPassWord(username,password);
    }

    @Ann(msg = "查看所有用户列表")
    public Page selectAllUser(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(userMapper.selectAllUser());
        Page page1 = new Page(pageInfo,0,"成功");
        return page1;
    }
    @Ann(msg = "通过id删除用户")
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
    @Ann(msg = "通过id修改用户信息")
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
    @Ann(msg = "通过id重置密码为ysd123")
    public Integer updatePwdByID(Integer id){
        System.out.println(id);
        return userMapper.updatePwdByID(id);
    }
    @Ann(msg = "用户锁定")
    public  boolean updateAddLock( Integer statue,Integer userid){
        return  userMapper.updateAddLock(statue,userid);
    }
    @Ann(msg = "用户解锁")
    public  boolean updateDelLock( Integer statue,Integer userid){
        return  userMapper.updateDelLock(statue,userid);
    }
}
