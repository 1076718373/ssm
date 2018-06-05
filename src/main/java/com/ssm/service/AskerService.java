package com.ssm.service;

import com.ssm.dao.AskerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AskerService {
    @Autowired
    private AskerMapper askerMapper;
}
