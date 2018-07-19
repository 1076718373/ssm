package com.ssm.service.mysql;

import com.ssm.bean.mysql.Module;
import com.ssm.bean.mysql.Page;
import com.ssm.dao.mysql.ModuleMapper;
import com.ssm.util.Ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    @Ann(msg = "根据角色的id来查询到所对应的各个模块id")
    public List<Integer> selectModuleIdByRolesId(Integer userId) {
        return moduleMapper.selectModuleIdByRolesId(userId);
    }

    @Ann(msg = "查角色没有的模块操作")
    public List<Module> selectNoModule(Integer id) {
        return moduleMapper.selectNoModule(id);
    }

    @Ann(msg = "查角色已有的模块操作")
    public List<Module> selectModule(Integer id) {
        return moduleMapper.selectModule(id);
    }

    @Ann(msg = "通过模块id查询模块信息")
    public Object selectModulesByModuleId(List<Integer> modules) {
        return moduleMapper.selectModulesByModuleId(modules);

    }

    @Ann(msg = "查询所有模块")
    public List<Module> selectAllModules() {
        return moduleMapper.selectAllModules();
    }

    @Ann(msg = "添加模块")
    public int insertModules(Module module) {
        return moduleMapper.insertSelective(module);
    }

    @Ann(msg = "修改模块")
    public int updateModuleById(Integer id, String name, Integer weight, String path, Integer parentid) {
        return moduleMapper.updateModuleById(id, name, weight, path, parentid);
    }

    @Ann(msg = "递归删除模块")
    public Page deleteModuleById(Integer id) {
        List<Module> childModules = moduleMapper.selectModuleByPrentId(id);
        int i = 0;
        Page page = null;
        if (childModules.size() == 0) {
            moduleMapper.deleteRoleIdByModuleId(id);
            i = moduleMapper.deleteByPrimaryKey(id);
        } else {
            moduleMapper.deleteRoleIdByModuleId(id);
            i = moduleMapper.deleteByPrimaryKey(id);
            for (Module module : childModules) {
                deleteModuleById(module.getId());
            }
        }

        if (i > 0) {
            page = new Page(0, "删除成功");
        } else {
            page = new Page(1, "删除失败");
        }
        return page;
    }


}
