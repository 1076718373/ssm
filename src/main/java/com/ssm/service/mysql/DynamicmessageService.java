package com.ssm.service.mysql;

import com.ssm.dao.mysql.DynamicmessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicmessageService {
    @Autowired
    private DynamicmessageMapper dynamicmessageMapper;
}
