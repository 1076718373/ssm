package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.mysql.Page;
import com.ssm.bean.mysql.User;
import com.ssm.service.mysql.RoleService;
import com.ssm.service.mysql.UserService;
import com.ssm.util.PasswordEncoder;
import com.ssm.util.PoiUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/user", name = "用户模块")
public class UserContriller {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PoiUtils poiUtils;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查看所有用户
     *
     * @param page  layui返回的数据，表示第几条数据
     * @param limit 表示一页显示数据的条数
     * @return 返回page对象
     */
    @GetMapping(value = "/allUser", name = "查询所有用户")
    public Page selectAllUser(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        PageInfo pageInfo = new PageInfo(userService.selectAllUser());
        Page page1 = new Page(pageInfo, 0, "成功");
        return page1;
    }

    /**
     * 通过用户id删除id功能
     *
     * @param userId 用户的id
     * @return 返回page对象
     */
    @PostMapping(value = "/deleteUser", name = "通过id删除用户")
    public Page deleteUserById(Integer userId) {
        return userService.deleteUserById(userId);
    }

    /**
     * 通过id修改用户信息
     *
     * @param userId 用户的id
     * @param mail   修改后的mail
     * @param tel    修改后的tel
     * @return 返回page对象
     */
    @PostMapping(value = "/updateUser", name = "通过id修改用户信息")
    public Page updateUserById(Integer userId, String mail, String tel) {
        return userService.updateUserById(userId, mail, tel);
    }


    /**
     * 重置密码
     *
     * @param id 被重置密码的用户ID
     * @return 返回给前端操作信息
     */
    @GetMapping(value = "/restPwd", name = "重置密码")
    public Page restPwd(Integer id) {
        Integer i = userService.updatePwdByID(id);
        Page page = null;
        if (i > 0) {
            page = new Page(150, "密码重置成功");

        } else {
            page = new Page(151, "密码重置失败");

        }
        return page;
    }

    /**
     * 更新锁定状态
     *
     * @param statue 1为没锁定，0为锁定
     * @return
     */
    @GetMapping(value = "updateLockStatue", name = "更新锁定状态")
    public Page updateLockStatue(Integer statue, Integer userid) {
        Page page = null;
        if (statue == 1) {
            userService.updateAddLock(statue, userid);
            page = new Page(160, "用户锁定成功");
        }

        if (statue == 0) {
            userService.updateDelLock(statue, userid);
            page = new Page(161, "用户解锁成功");
        }
        return page;
    }

    /**
     * 按照用户输入的条件检索信息
     *
     * @param page  检索后从第page条展示数据
     * @param limit 一页展示数据的条数
     * @param name  通过用户名检索数据
     * @param lock  通过是否锁定检索数据
     * @return 返回page对象
     */
    @GetMapping(value = "/selectUser", name = "按照条件查询用户")
    public Page selectUserByNameOrLock(Integer page, Integer limit, String name, String lock) {
        PageHelper.startPage(page, limit);
        PageInfo pageInfo = new PageInfo(userService.selectUserByNameOrLock(name, lock));
        Page page1 = new Page(pageInfo, 0, "success");
        return page1;
    }

    /**
     * 为用户设置角色
     *
     * @param userId 用户的id
     * @param roleId 角色对应的id
     * @return 返回page对象
     */
    @PostMapping(value = "/setRole", name = "为用户设置角色")
    public Page setRoleByUserId(Integer userId, Integer roleId) {
        int n = userService.insertRoleByUserId(userId, roleId);
        Page page = null;
        if (n > 0) {
            page = new Page(0, "设置成功");
        } else {
            page = new Page(1, "设置失败");
        }
        return page;
    }


    /**
     * 查询角色对应的id
     *
     * @return 返回list集合  （角色名：角色id）
     */
    @GetMapping(value = "/selectRole", name = "查询所有角色名称对应的id")
    public List<Map> selectAllRole() {
        List<String> roleName = roleService.selectAllRoleName();
        List<Integer> roleId = roleService.selectAllRoleId();
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < roleId.size(); i++) {
            Map map = new HashMap();
            map.put(roleName.get(i), roleId.get(i));
            list.add(map);
        }
        return list;
    }

    /**
     * 添加新用户
     *
     * @param addName  新用户名
     * @param addPwd   新用户密码
     * @param addEmail 新用户Email
     * @param addPhone 新用户联系方式
     * @return
     */
    @PostMapping(value = "/addUser", name = "添加用户")
    public Page addUser(String addName, String addPwd, String addEmail, String addPhone) {
        PasswordEncoder passwordEncoder = new PasswordEncoder("abc", "MD5");
        addPwd = passwordEncoder.encode(addPwd, 5);
        int add = userService.insertAddUser(addName, addPwd, addEmail, addPhone);
        Page page = null;
        if (add > 0) {
            page = new Page(0, "添加用户成功");
        } else {
            page = new Page(1, "添加用户失败");
        }
        return page;
    }

    @GetMapping(value = "/downExcle", name = "导出Excle表格信息")
    public void downExcle(HttpServletResponse response) throws IOException {
        //定义表头
        String[] tableHander = {"登录名", "密码", "是否锁定", "最后一次登录时间", "密码错误次数", "被锁定时间", "密保邮箱", "密保手机号", "账户创立时间",
                "上次登录ip"};
        //创建工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //设置单元格样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建第一个工作表
        HSSFSheet sheet = hssfWorkbook.createSheet("学生表");
        //创建第一行（表头）
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < tableHander.length; i++) {
            //创建单元格
            HSSFCell cell = row.createCell(i);
            //为单元格赋值
            cell.setCellValue(tableHander[i]);
            //设置单元格居中
            cell.setCellStyle(cellStyle);
            //自动添加列
            sheet.autoSizeColumn(i);
            //列宽
            sheet.setColumnWidth(i, 50 * 100);
        }
        List<User> users = userService.selectAllUser();


        for (int i = 0; i < users.size(); i++) {
            //从第二行开始
            HSSFRow row1 = sheet.createRow(i + 1);
            // 每一行对应的用户
            User user = users.get(i);
            // 给每个单元格赋值
            row1.createCell(0).setCellValue(user.getLoginname());
            row1.createCell(1).setCellValue(user.getPassword());
            row1.createCell(2).setCellValue(user.getIslockout());
            row1.createCell(3).setCellValue(simpleDateFormat.format(user.getLastlogintime()));
            row1.createCell(4).setCellValue(user.getPsdwrongtime());
            row1.createCell(5).setCellValue(simpleDateFormat.format(user.getLocktime()));
            row1.createCell(6).setCellValue(user.getProtectemail());
            row1.createCell(7).setCellValue(user.getProtectmtel());
            row1.createCell(8).setCellValue(simpleDateFormat.format(user.getCreatetime()));
            row1.createCell(9).setCellValue(user.getLoginip());
        }
        //设置文件名和后缀
        String fileName = "用户.xls";
        //避免下载出现中文乱码
        fileName = URLEncoder.encode(fileName, "UTF8");
        //开始输出工作簿
        OutputStream outputStream = response.getOutputStream();
        // 重置response设置
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        hssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @PostMapping(value = "/uploadExcle", name = "上传Excle表格")
    public Page uploadExcle(@RequestParam("file") MultipartFile file) throws ParseException {
        List<String[]> workbookValue = poiUtils.getWorkbookValue(file);
        List<User> users = new ArrayList<>();
        for (String[] str : workbookValue) {
            User user = new User();
            user.setLoginname(str[0]);
            user.setPassword(str[1]);
            user.setIslockout(str[2]);
            user.setLastlogintime(simpleDateFormat.parse(str[3]));
            user.setPsdwrongtime(Integer.parseInt(str[4]));
            user.setLocktime(simpleDateFormat.parse(str[5]));
            user.setProtectemail(str[6]);
            user.setProtectmtel(str[7]);
            user.setCreatetime(simpleDateFormat.parse(str[8]));
            user.setLoginip(str[9]);
            users.add(user);
        }
        int n = userService.insertUser(users);
        Page page = null;
        if (n > 0) {
            page = new Page(100, "成功");
        } else {
            page = new Page(101, "失败");
        }
        return page;
    }
}
