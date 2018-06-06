package com.ssm.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

/**
 * 这个类就是登录的过滤器的工具类
 */
public class LoginFinal {
    //可直接通过过滤器的字符路径
    public static final String PATH="";

    /**
     *此方法获得用户所访问url的地址
     * 转化为数据库中存储的权限地址的格式
     * 就是为了对比是否达到访问的权限
     * @param handler
     * @return
     */
    public static String getMethodValue(HandlerMethod handler) {
        String name = handler.getMethod().getAnnotation(RequestMapping.class).name();
        if(name.equals("")||name==null){
            return null;
        }
        String methodValue = handler.getMethod().getAnnotation(RequestMapping.class).value()[0];
        String classValue=handler.getBeanType().getAnnotation(RequestMapping.class).value()[0];

        return (classValue+":"+methodValue).replace("/","");
    }
}
