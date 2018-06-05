package com.ssm.dao;


import com.ssm.bean.Module;
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

    List<Integer> selectModuleIdByRolesId(List<Integer> list);
}