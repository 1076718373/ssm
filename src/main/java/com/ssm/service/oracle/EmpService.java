package com.ssm.service.oracle;

import com.ssm.bean.oralce.Emp;
import com.ssm.dao.oracle.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    EmpMapper empMapper;

    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }
}
