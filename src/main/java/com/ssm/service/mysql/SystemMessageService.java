package com.ssm.service.mysql;

import com.ssm.dao.mysql.SystemlogMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemMessageService {
    @Autowired
    private SystemlogMessageMapper systemlogMessageMapper;
}
