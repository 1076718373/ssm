package com.ssm.service;

import com.ssm.dao.UserCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckService {
    @Autowired
    private UserCheckMapper userCheckMapper;
}
