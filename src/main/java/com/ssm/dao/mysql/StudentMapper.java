package com.ssm.dao.mysql;


import com.ssm.bean.mysql.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllStudents();

    List<Student> queryStudent(@Param("name") String name, @Param("phone") String phone,
                               @Param("zixunshi") String zixunshi, @Param("isreturnvisit") String isreturnvisit,
                               @Param("ispay") String ispay, @Param("isValid") String isValid, @Param("sex") String sex);

    int updateAskerid(@Param("askerid") Integer askerid, @Param("id") Integer id);
}