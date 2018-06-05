package com.ssm.service;

import com.ssm.dao.ModuleMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    @Ann(msg = "根据角色的id来查询到所对应的各个模块id")
    public List<Integer> selectModuleIdByRolesId(List<Integer> rolesId) {
        return moduleMapper.selectModuleIdByRolesId(rolesId);
    }
}
