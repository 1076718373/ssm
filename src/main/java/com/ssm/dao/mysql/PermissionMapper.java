package com.ssm.dao.mysql;


import com.ssm.bean.mysql.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> selectURL();

    Integer insertPermissions(List<Permission> list);

    List<String> selectPermissionByUserId(Integer userId);


    List<Permission> selectRolePermissions(Integer id);

    List<Permission> selectRoleNoPermissions(Integer id);

    Integer insertPermissionAndRole(@Param("roleId") Integer roleId, @Param("perId") Integer perId);

    Integer deletePermissionAndRole(@Param("roleId") Integer roleId, @Param("perId") Integer perId);
}