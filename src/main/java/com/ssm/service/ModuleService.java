package com.ssm.service;

import com.ssm.dao.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    public List<Integer> selectModuleIdByRolesId(List<Integer> rolesId) {
        return moduleMapper.selectModuleIdByRolesId(rolesId);
    }
}
