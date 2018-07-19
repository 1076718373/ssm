package com.ssm.controller;


import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.Student;
import com.ssm.service.mysql.StudentService;
import com.ssm.util.PoiUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/StudentMessUD", name = "学生信息Excel导出和上传")
public class StudentMessController {

    @Autowired
    StudentService studentService;
    @Autowired
    PoiUtils poiUtils;

    @GetMapping(value = "/exportExcel", name = "导出学生信息表")
    public void exportExcel(HttpServletResponse response) throws IOException {

        //定义表头
        String[] tableHeader = {"姓名", "性别", "QQ号", "电话", "微信", "登记人",
                "咨询师", "是否缴费", "是否回访", "进班时间", "预付定金", "首次回访时间"};

        //创建工作薄(HSSFWorkbook 创建xls工作簿  XSSFWorkbook 创建xlxs工作簿)
        HSSFWorkbook workbook = new HSSFWorkbook();

        //给工作簿设置一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);  //居中

        //创建第一个工作表
        HSSFSheet sheet = workbook.createSheet("学生信息表");

        //创建第一行
        HSSFRow row = sheet.createRow(0);


        for (int i = 0; i < tableHeader.length; i++) {
            //创建单元格
            HSSFCell cell = row.createCell(i);
            //给单元格设置内容
            cell.setCellValue(tableHeader[i]);
            //给单元格添加样式居中
            cell.setCellStyle(style);
            //自动添加列
            sheet.autoSizeColumn(i);
            //设置列宽
            sheet.setColumnWidth(i, 50 * 100);
        }

        //获取要导出的所有学生
        List<Student> list = studentService.exportStudentExcel();

        //格式化时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            // 从第二行开始
            HSSFRow hssfRow = sheet.createRow(i + 1);
            // 每一行对应的学生
            Student students = list.get(i);
            // 给每个单元格赋值
            hssfRow.createCell(0).setCellValue(students.getName());
            hssfRow.createCell(1).setCellValue(students.getSex());
            hssfRow.createCell(2).setCellValue(students.getQq());
            hssfRow.createCell(3).setCellValue(students.getPhone());
            hssfRow.createCell(4).setCellValue(students.getWeixin());
            hssfRow.createCell(5).setCellValue(students.getCreateuser());
            hssfRow.createCell(6).setCellValue(students.getZixunname());
            hssfRow.createCell(7).setCellValue(students.getIspay());
            hssfRow.createCell(8).setCellValue(students.getIsreturnvist());
            hssfRow.createCell(9).setCellValue(simpleDateFormat.format(students.getInclasstime()));
            hssfRow.createCell(10).setCellValue(students.getPremoney());
            hssfRow.createCell(11).setCellValue(simpleDateFormat.format(students.getFirstvisittime()));
        }

        //设置excel文件名称
        long downLoadtime = System.currentTimeMillis();
        String fileName = downLoadtime + ".xls";

        //开始输出工作簿
        OutputStream outputStream = response.getOutputStream();
        // 重置response设置
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        // 发送工作簿
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }


    /**
     * 用户上传excel,解析excel,将excel数据加入到数据库中
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadStudentExcel")
    public Page uploadStuByExcel(@RequestParam("file") MultipartFile file) throws ParseException {

        List<String[]> strings = poiUtils.getWorkbookValue(file);

        List<Student> students = new ArrayList<>();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (String[] str : strings) {

            Student student = new Student();
            student.setName(str[1]);
            student.setAge(Integer.valueOf(str[2]));
            student.setSex(str[3]);
            student.setPhone(str[4]);
            student.setStustatus(str[5]);


            student.setPerstate(str[6]);
            student.setMsgsource(str[7]);
            student.setSourceurl(str[8]);
            student.setSourcekeyword(str[9]);
            student.setAddress(str[10]);


            student.setNetpusherid(str[11]);
            student.setAskerid(str[12]);
            student.setQq(str[13]);
            student.setWeixin(str[14]);
            student.setContent(str[15]);


            student.setCreatetime(simpleDateFormat.parse(str[16]));
            student.setLearnforward(str[17]);
            student.setIsvalid(str[18]);
            student.setRecord(str[19]);
            student.setIsreturnvist(str[20]);
            student.setFirstvisittime(simpleDateFormat.parse(str[21]));


            student.setIshome(str[22]);
            student.setHometime(simpleDateFormat.parse(str[23]));
            student.setLostvalid(str[24]);
            student.setIspay(str[25]);
            student.setPaytime(simpleDateFormat.parse(str[26]));

            student.setMoney(Double.valueOf(str[27]));
            student.setIsreturnmoney(str[28]);
            student.setIsinclass(str[29]);
            student.setInclasstime(simpleDateFormat.parse(str[30]));
            student.setInclasscontent(str[31]);

            student.setAskercontent(str[32]);
            student.setIsdel(str[33]);
            student.setFrompart(str[34]);
            student.setStuconcern(str[35]);
            student.setIsbaobei(str[36]);


            student.setZixunname(str[37]);
            student.setCreateuser(str[38]);
            student.setReturnmoneyreason(str[39]);
            student.setPremoney(Double.valueOf(str[40]));
            student.setPremoneytime(simpleDateFormat.parse(str[41]));


            students.add(student);

        }

        return studentService.batchStudents(students);

    }
}
