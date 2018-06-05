package com.ssm.service;

import com.ssm.dao.RoleMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Ann(msg = "根据用户id来查询到此用户具有的所有的角色id")
    public List<Integer> selectRoleIdByUserId(Integer id) {
        return  roleMapper.selectRoleIdByUserId(id);
    }
}
