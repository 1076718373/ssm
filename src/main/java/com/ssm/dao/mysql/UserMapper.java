package com.ssm.dao.mysql;

import com.ssm.bean.mysql.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByNameAndPassWord(@Param("username") String username, @Param("password") String password);

    List<User> selectAllUser();

    int updateUserById(@Param("userId") Integer userId, @Param("mail") String mail, @Param("tel") String tel);

    int updatePwdByID(Integer id);

    boolean updateAddLock(@Param("statue") Integer statue, @Param("userid") Integer userid);

    boolean updateDelLock(@Param("statue") Integer statue, @Param("userid") Integer userid);


    List<User> selectUserByNameOrLock(@Param("name") String name, @Param("lock") String lock);

    int insertRoleByUserId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    //添加新用户
    int insertAddUser(@Param("addName") String addName, @Param("addPwd") String addPwd, @Param("addEmail") String addEmail, @Param("addPhone") String addPhone);

    int deleteUserRoleByUserId(Integer userId);

    int udateUserIPAndTimeByUsername(@Param("username") String username, @Param("date") Date date, @Param("remoteHost") String remoteHost);

    int updateLoginTimeByUsername(String username);
}