package com.ssm.service;

import com.ssm.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Integer> selectRoleIdByUserId(Integer id) {
        return  roleMapper.selectRoleIdByUserId(id);
    }
}
