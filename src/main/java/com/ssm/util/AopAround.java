package com.ssm.util;

import com.google.gson.Gson;
import com.ssm.bean.SystemlogMessage;
import com.ssm.dao.SystemlogMessageMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect//声明切面
public class AopAround implements Ordered {
    Gson gson = new Gson();

    @Autowired
    private SystemlogMessageMapper systemlogMessageMapper;

    @Override//确定执行的顺序
    public int getOrder() {
        return 1;
    }

    /**
     * 环绕切面（只环绕加了Ann注解的方法）
     * @param joinPoint
     * @return
     */
    @Around("@annotation(com.ssm.util.Ann)")
    public Object aroundAop(ProceedingJoinPoint joinPoint){
        Gson gson =new Gson();
        Object obj=null;
        //日志信息类
        SystemlogMessage systemlogMessage=new SystemlogMessage();
        systemlogMessage.setSystemlogmessageRoles("role");
        systemlogMessage.setSystemlogmessageName("root");

        Map<String,String>  map= getServiceMethodDesc(joinPoint);
        systemlogMessage.setSystemlogmessageDescription(map.get("msg"));
        systemlogMessage.setSystemlogmessageParams(map.get("args"));
        systemlogMessage.setSystemlogmessagMethod(map.get("methodName"));
        long startTime = System.currentTimeMillis();
        Date date = new Date(startTime);
        Object[] objects= joinPoint.getArgs();
        systemlogMessage.setSystemlogmessageStarttime(date);
        try {
            obj = joinPoint.proceed(objects);
            systemlogMessage.setSystemlogmessageSuccessful("ok");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            systemlogMessage.setSystemlogmessageSuccessful("no");
            systemlogMessage.setSystemlogmessageException(throwable.getMessage());
        }
        long endTime = System.currentTimeMillis();
        systemlogMessage.setSystemlogmessageTime((endTime-startTime)+"ms");

        systemlogMessageMapper.insertSelective(systemlogMessage);//插入日志信息
        return obj;
    }

    /**
     * 判断向日志表中存储的是参数还是参数类型
     * @param joinPoint
     * @return
     */
    private Map<String,String> getServiceMethodDesc(ProceedingJoinPoint joinPoint) {
        Map<String ,String> map = new HashMap<>();
        Class clazz=joinPoint.getTarget().getClass();//得到访问的方法的运行时类对象
        Method[] methods= clazz.getMethods();
        String methodName=joinPoint.getSignature().getName();
        map.put("methodName",methodName);
        Object[] params=joinPoint.getArgs();
        for(Method m:methods){
            if(methodName.equals(m.getName())){
                if(m.getParameterTypes().length==params.length){
                    map.put("msg",m.getAnnotation(Ann.class).msg());//保存service方法上的描述
                    if(m.getAnnotation(Ann.class).flag()){
                        map.put("args",gson.toJson(params));
                    }else{
                        String str ="";
                        for (Object o:params){
                            str+=o.getClass().getTypeName();
                        }
                        map.put("args",str);
                    }
                }
            }
        }
        return map;
    }


}
