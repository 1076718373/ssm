package com.ssm.anchor.interceptor;

import com.ssm.anchor.exception.NoLoginExcepetion;
import com.ssm.anchor.exception.NoPermissionException;
import com.ssm.util.LoginFinal;
import com.ssm.util.ToWebToken;
import com.ssm.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private Token token;

    /**
     * 访问的过滤器，拦截不合法的各种请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path=request.getServletPath();
        if(path.matches(LoginFinal.PATH)){
            return true;
        }

        String tokenStr = request.getParameter("token");

        if(tokenStr==null||tokenStr.equals("")){
            throw new NoLoginExcepetion("对不起，你未登录，请登陆");
        }
        List<String> permissions=token.getObject(ToWebToken.class,tokenStr).getPermissions();//解密token

        if(handler instanceof HandlerMethod){
            String urlValue=LoginFinal.getMethodValue((HandlerMethod) handler);
            if(urlValue!=null) {
                if (!permissions.contains(urlValue)) {
                    throw new NoPermissionException("对不起，您没有权限访问该资源");
                }
            }else {
                return true;
            }
        }
        return true;
    }
}
