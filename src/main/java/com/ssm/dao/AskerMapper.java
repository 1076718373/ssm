package com.ssm.dao;

import com.ssm.bean.Asker;
import org.springframework.stereotype.Repository;

@Repository
public interface AskerMapper {
    int deleteByPrimaryKey(Integer askerid);

    int insert(Asker record);

    int insertSelective(Asker record);

    Asker selectByPrimaryKey(Integer askerid);

    int updateByPrimaryKeySelective(Asker record);

    int updateByPrimaryKey(Asker record);
}