package com.ssm.anchor.interceptor;


import com.ssm.anchor.exception.NoLoginExcepetion;
import com.ssm.util.LoginFinal;
import com.ssm.util.ToWebToken;
import com.ssm.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 拦截器,拦截所有springMVC请求,获得请求的信息
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    Token token;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户访问地址
        String path = request.getServletPath();
        // URL满足条件,放行
        if (path.matches(LoginFinal.PATH)) {
            return true;
        }
        //请求默认的Servlet资源时handler =>   org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler
        //请求SpringMVC静态资源时handler =>  org.springframework.web.servlet.resource.ResourceHttpRequestHandler
        //请求SpringMVC控制器时handler =>   org.springframework.web.method.HandlerMethod

        //URL访问Controller
        if (handler instanceof HandlerMethod) {

            String token1 = request.getParameter("token");

            //获取该用户的所有权限
            List<String> userJurisdictions = null;
            if (token == null || "".equals(token)) {
                throw new NoLoginExcepetion("对不起,还没登录401");
            }
            ToWebToken myToken = token.getObject(ToWebToken.class, token1);

            System.out.println("?????????????????" + myToken.getName());

            if (myToken != null) {
                userJurisdictions = myToken.getPermissions();
                String redisToken = (String) redisTemplate.opsForValue().get(myToken.getName());
                System.out.println(redisToken);
                System.out.println(token1);
                System.out.println(userJurisdictions);
                if (redisToken == null || !redisToken.equals(token1) || userJurisdictions == null) {
                    throw new NoLoginExcepetion("对不起,还没登录402");
                }

            }

            //得到请求映射方法
            HandlerMethod method = (HandlerMethod) handler;
            System.out.println(">>>>>>>>>>>" + method.getMethod().getName());
            //得到访问所请求方法应该得到的权限
            String permissionValue = LoginFinal.getMethodValue(method);

            if (!userJurisdictions.contains(permissionValue)) {
                throw new NoPermissionException("对不起,你无权访问");
            }
        }
        return super.preHandle(request, response, handler);
    }

}