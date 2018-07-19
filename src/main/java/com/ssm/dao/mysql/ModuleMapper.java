package com.ssm.dao.mysql;


import com.ssm.bean.mysql.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Integer> selectModuleIdByRolesId(Integer userId);

    List<Module> selectModulesByModuleId(List<Integer> list);

    List<Module> selectAllModules();

    int updateModuleById(@Param("id") Integer id, @Param("name") String name, @Param("weight") Integer weight, @Param("path") String path, @Param("parentid") Integer parentid);

    int deleteRoleIdByModuleId(Integer id);

    List<Module> selectNoModule(Integer id);

    List<Module> selectModule(Integer id);

    Module selectModulesById(Integer id);

    List<Module> selectModuleByPrentId(Integer parentid);
}