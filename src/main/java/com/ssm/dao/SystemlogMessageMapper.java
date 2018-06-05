package com.ssm.dao;


import com.ssm.bean.SystemlogMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemlogMessageMapper {
    int deleteByPrimaryKey(Integer idsystemlogmessage);

    int insert(SystemlogMessage record);

    int insertSelective(SystemlogMessage record);

    SystemlogMessage selectByPrimaryKey(Integer idsystemlogmessage);

    int updateByPrimaryKeySelective(SystemlogMessage record);

    int updateByPrimaryKeyWithBLOBs(SystemlogMessage record);

    int updateByPrimaryKey(SystemlogMessage record);
}