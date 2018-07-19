package com.ssm.service.mysql;

import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Student;
import com.ssm.dao.mysql.StudentMapper;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;


    public List selectAllStudents() {
        return studentMapper.selectAllStudents();
    }


    public List<Student> exportStudentExcel() {

        return studentMapper.selectAllStudents();
    }

    public Page batchStudents(List<Student> students) {
        boolean flag = true;
        for (Student s : students) {
            Integer count = studentMapper.insertSelective(s);
            if (count < 1) {
                flag = false;
            }
        }
        Page page = flag ? new Page(200, "上传数据成功") : new Page(500, "系统繁忙请稍后再试");
        return page;
    }

    public List<Student> selectStudents(String name, String phone, String zixunshi, String isreturnvisit, String ispay, String isValid, String sex) {
        return studentMapper.queryStudent(name, phone, zixunshi, isreturnvisit, ispay, isValid, sex);
    }

    public int insertStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    public int updateAskerid(Integer askerid, Integer id) {
        return studentMapper.updateAskerid(askerid, id);
    }
}
