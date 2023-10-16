package com.bjsxt.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String url;
    public MyAuthenticationFailureHandler(String url){
        this.url = url;
    }
    /**
     * 认证失败处理
     * @param request
     * @param response
     * @param exception 认证失败异常，包含用户名不存在，密码错误等。
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
