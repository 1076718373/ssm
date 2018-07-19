package com.ssm.dao.mysql;

import com.ssm.bean.mysql.Asker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AskerMapper {
    int deleteByPrimaryKey(Integer askerid);

    int insert(Asker record);

    int insertSelective(Asker record);

    Asker selectByPrimaryKey(Integer askerid);

    int updateByPrimaryKeySelective(Asker record);

    int updateByPrimaryKey(Asker record);

    List<Asker> selectAllAsker();

}