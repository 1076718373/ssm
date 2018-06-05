package com.ssm.dao;


import com.ssm.bean.UserCheck;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCheck record);

    int insertSelective(UserCheck record);

    UserCheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCheck record);

    int updateByPrimaryKey(UserCheck record);
}