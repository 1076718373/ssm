package com.ssm.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//设置注解的声明周期
@Target(ElementType.METHOD)//加载方法上的注解
@Inherited
public @interface Ann {
    String msg() default "";
    boolean flag() default true;//确定像日志表中存储参数还是参数类型
}
