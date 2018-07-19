package com.ssm.service.mysql;

import com.ssm.dao.mysql.UserCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckService {
    @Autowired
    private UserCheckMapper userCheckMapper;
}
