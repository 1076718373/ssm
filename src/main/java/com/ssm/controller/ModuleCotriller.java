package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.mysql.Module;
import com.ssm.bean.mysql.Page;
import com.ssm.service.mysql.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/modules", name = "模块系统")
public class ModuleCotriller {
    @Autowired
    ModuleService moduleService;

    @GetMapping(value = "/meun", name = "菜单模块")
    public Object selectModulesByModuleId(@RequestParam("modulesId") List<Integer> modulesId) {
        System.out.println(">>>>>>>>>>>>>>>>>>" + modulesId.toString());
        return moduleService.selectModulesByModuleId(modulesId);
    }

    @GetMapping(value = "/AllModule", name = "查询所有模块")
    public Page selectAllModules(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        PageInfo pageInfo = new PageInfo(moduleService.selectAllModules());
        Page page1 = new Page(pageInfo, 0, "succecc");
        return page1;
    }

    @PostMapping(value = "/addModule", name = "添加模块")
    public Page addModule(Module module) {
        int n = moduleService.insertModules(module);
        Page page = null;
        if (n > 0) {
            page = new Page(0, "添加成功");
        } else {
            page = new Page(1, "添加失败");
        }
        return page;
    }

    @GetMapping(value = "/mosuleMess", name = "查询所有模块信息")
    public List<Module> selectAllModules() {
        return moduleService.selectAllModules();
    }

    @PostMapping(value = "/modifyModule", name = "修改模块信息")
    public Page updateModuleById(Integer id, String name, Integer weight, String path, Integer parentid) {
        int n = moduleService.updateModuleById(id, name, weight, path, parentid);
        Page page = null;
        if (n > 0) {
            page = new Page(0, "修改成功");
        } else {
            page = new Page(1, "修改失败");
        }
        return page;
    }

    @PostMapping(value = "/deleteModule", name = "删除模块")
    public Page deleteModuleById(Integer id) {
        return moduleService.deleteModuleById(id);
    }
}
