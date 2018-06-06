package com.ssm.controller;

import com.ssm.bean.Permission;
import com.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Inherited;
import java.util.*;

@RequestMapping("/permission")
@CrossOrigin
@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping(value = "/update",name = "更新权限信息")
    public Object insertPermissions(){
        Integer i = permissionService.insertPermissions(getNewPermission());
        return "新增了"+i+"个权限";
    }

    /**
     * 得到所有新增还未加入数据的权限
     * @return
     */
    private synchronized List<Permission> getNewPermission() {
        List<String> oldPermissionURL = permissionService.selectURL();
        List<Permission> resultList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map =
                requestMappingHandlerMapping.getHandlerMethods();

        Collection<HandlerMethod> handlerMethods = map.values();
        if(handlerMethods==null||handlerMethods.isEmpty()){
            return null;
        }

        //遍历所有方法
        for(HandlerMethod m:handlerMethods){
            RequestMapping methodMapping = m.getMethodAnnotation(RequestMapping.class);
            String mappingName = methodMapping.name();
            if(!mappingName.equals("")){
                Permission p= new Permission();
                String methodValue= methodMapping.value()[0];
                RequestMapping classMapping=m.getBeanType().getAnnotation(RequestMapping.class);
                String className=classMapping.name();
                String classValue=classMapping.value()[0];
                String permissionValue=(classValue+":"+methodValue).replace("/","");
                if(oldPermissionURL.size()>0){
                    if(!oldPermissionURL.contains(permissionValue)){
                        p.setPermissionlastupdatetime(new Date());
                        p.setPermissionmodule(className);
                        p.setPermissionvalue(permissionValue);
                        p.setPermissionname(mappingName);
                        resultList.add(p);
                    }
                }else{
                    p.setPermissionlastupdatetime(new Date());
                    p.setPermissionmodule(className);
                    p.setPermissionvalue(permissionValue);
                    p.setPermissionname(mappingName);
                    resultList.add(p);
                }

            }
        }


        return resultList;
    }

}
