package com.ssm.dao.mysql;


import com.ssm.bean.mysql.SystemlogMessage;
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