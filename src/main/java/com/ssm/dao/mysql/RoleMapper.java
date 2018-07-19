package com.ssm.dao.mysql;


import com.ssm.bean.mysql.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Integer> selectRoleIdByUserId(Integer id);

    String selectRoleByUserId(Integer userId);

    List<String> selectAllRoleName();

    List<Integer> selectAllRoleId();

    List<Role> selectRoleByName(String name);

    List<Role> selectRoleByModuleId(Integer moduleId);

    Integer insertRole(String name);

    Integer deleteRoleById(Integer id);

    Integer updateRoleById(@Param("id") Integer id, @Param("name") String name);

    Integer InsertRoleAndModuleById(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);

    Integer selectRoleAndModuleById(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);

    List<Role> selectAll();

    Integer deleteRoleAndModuleByRoleId(Integer id);

    Integer deleteRoleAndPermissionByRoleId(Integer id);

    Integer deleteRoleAndUserByRoleId(Integer id);

    Integer deleteModuleAndRoleByModuleId(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);


    List<Role> queryAllRoles();

    //给用户设置角色
    Integer updateRoleName(@Param("newRoleName") String newRoleName, @Param("oldRoleName") String oldRoleName);

}