package com.ssm.dao;


import com.ssm.bean.Dynamicmessage;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicmessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dynamicmessage record);

    int insertSelective(Dynamicmessage record);

    Dynamicmessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dynamicmessage record);

    int updateByPrimaryKey(Dynamicmessage record);
}