package com.ssm.service;

import com.ssm.dao.SystemlogMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemMessageService {
    @Autowired
    private SystemlogMessageMapper systemlogMessageMapper;
}
