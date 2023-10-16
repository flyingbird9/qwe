package com.bjsxt.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功后的处理器。
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    // 就是登录认证成功后重定向的地址
    private String url;
    public MyAuthenticationSuccessHandler(){

    }
    public MyAuthenticationSuccessHandler(String url){
        this.url = url;
    }
    /**
     * 认证成功后，执行的逻辑。
     * @param request 请求
     * @param response 应答
     * @param authentication 认证成功后的用户主体对象。相当于登录用户
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getClass().getName());
        System.out.println("getPrincipal - " + authentication.getPrincipal());
        System.out.println("getCredentials - " + authentication.getCredentials());
        System.out.println("getDetails - " + authentication.getDetails());
        // 重定向到url地址。
        response.sendRedirect(url);
    }
}
