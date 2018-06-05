package com.ssm.service;

import com.ssm.dao.DynamicmessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicmessageService {
    @Autowired
    private DynamicmessageMapper dynamicmessageMapper;
}
