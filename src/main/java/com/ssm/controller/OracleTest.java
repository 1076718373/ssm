package com.ssm.controller;

import com.ssm.bean.oralce.Emp;
import com.ssm.service.oracle.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/oracle")
public class OracleTest {
    @Autowired
    EmpService empService;

    @GetMapping("/test")
    public List<Emp> selectAll() {
        return empService.selectAll();
    }

}
