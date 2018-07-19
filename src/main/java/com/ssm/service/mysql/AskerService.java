package com.ssm.service.mysql;

import com.ssm.bean.mysql.Asker;
import com.ssm.dao.mysql.AskerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AskerService {
    @Autowired
    private AskerMapper askerMapper;

    public List<Asker> selectAllAsker() {
        return askerMapper.selectAllAsker();
    }
}
