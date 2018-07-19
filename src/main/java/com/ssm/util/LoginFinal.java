package com.ssm.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

/**
 * 这个类就是登录的过滤器的工具类
 */
public class LoginFinal {
    //可直接通过过滤器的字符路径
    public static final String PATH = ".*/((login)|(system)).*";

    /**
     * 此方法获得用户所访问url的地址
     * 转化为数据库中存储的权限地址的格式
     * 就是为了对比是否达到访问的权限
     *
     * @param handler
     * @return
     */
    public static String getMethodValue(HandlerMethod handler) {
        RequestMapping requestMapping = handler.getMethodAnnotation(RequestMapping.class);
        //name不为空,意味着访问该方法需要权限
        if (!"".equals(requestMapping.name())) {
            //获取该方法所在类的requestMapping注解
            RequestMapping classRequestMapping = handler.getBeanType().getAnnotation(RequestMapping.class);
            //获取方法所在类的访问权限
            String modulePermission = classRequestMapping.value()[0];
            //获取访问该方法的完整权限
            String permission = (modulePermission + ":" + requestMapping.value()[0]).replace("/", "");
            return permission;
        }
        return "公共资源";
    }
}

