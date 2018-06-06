package com.ssm.dao;

import com.ssm.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByNameAndPassWord(@Param("username") String username,@Param("password") String password);

    List<User> selectAllUser();

    int updateUserById(@Param("userId") Integer userId,@Param("mail") String mail,@Param("tel") String tel);

    int updatePwdByID(Integer id);

    boolean updateAddLock(@Param("statue") Integer statue,@Param("userid") Integer userid);
    boolean updateDelLock(@Param("statue") Integer statue,@Param("userid") Integer userid);
    User selectUserByName(String name);

    Integer selectUserAddRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    Integer insertUserAddRole(@Param("userId") Integer userId, @Param("roleId")Integer roleId);
}