package com.ssm.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssm.bean.mysql.User;
import com.ssm.dao.mysql.MPTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test2")
public class MPTestController {
    @Autowired
    private MPTestMapper testMapper;

    @GetMapping("/test1")
    public List<User> select() {
        List<Integer> listid = new ArrayList<>();
        listid.add(8);
        listid.add(13);
        return testMapper.selectBatchIds(listid);
    }

    //根据条件查询
    @GetMapping("/test3")
    public List<User> select1() {
        List<User> users = testMapper.selectList(new EntityWrapper<User>()
                .between("Id", 20, 40).eq("IsLockout", "否")
        );
        return users;
    }


}
