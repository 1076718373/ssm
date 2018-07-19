package com.ssm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.mysql.Asker;
import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Student;
import com.ssm.service.mysql.AskerService;
import com.ssm.service.mysql.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/students", name = "学生信息")
public class StudentMessageController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AskerService askerService;


    @GetMapping(value = "/studentsMessage", name = "查询所有学生信息")
    public Page selectStudentMess(Integer page, Integer limit, String name, String phone, String zixunshi,
                                  String isreturnvisit, String ispay, String isValid, String sex) {
        PageHelper.startPage(page, limit);
        PageInfo pageInfo = new PageInfo(studentService.selectStudents(name, phone, zixunshi, isreturnvisit, ispay, isValid, sex));
        Page page1 = new Page(pageInfo, 0, "所有学生信息查询成功");
        return page1;
    }

    @PostMapping(value = "/addStudent", name = "添加学生")
    public Page insertStudent(Student students) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + students.toString());
        int n = studentService.insertStudent(students);
        Page page = null;
        if (n > 0) {
            page = new Page(100, "添加成功");
        } else {
            page = new Page(101, "添加失败");
        }
        return page;
    }

    @GetMapping("/allAsker")
    public List<Asker> selectAllAsker() {
        List<Asker> list = askerService.selectAllAsker();
        return list;
    }

    @PostMapping(value = "/updateAskerid", name = "为学生分配老师")
    public Page updateAskerid(Integer askerid, Integer id) {
        int n = studentService.updateAskerid(askerid, id);
        Page page = null;
        if (n > 0) {
            page = new Page(102, "分配成功");
        } else {
            page = new Page(103, "分配失败");
        }
        return page;
    }

}
