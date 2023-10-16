package com.bjsxt.config;

import com.bjsxt.handler.MyAuthenticationFailureHandler;
import com.bjsxt.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 给Spring Security框架增加配置
 * 需要继承一个Security框架提供的配置适配器（Adapter）. 虽然已过时，但是新实现类型 WebSecurityConfiguration中仍旧使用此类型。
 * 或者实现Security框架提供的配置接口。
 */
@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 配置方法。 父类型中定义的受保护配置方法。是对接口WebSecurityConfigurer中的configure方法的补充。
     * @param http 基于HTTP协议提供的Security配置类型。 所有的配置都基于当前参数实现。
     *
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * Security框架，默认拦截除 /login 地址以外的所有请求地址。
         * 只要客户端未经过Security框架认证，都统一重定向到登录页面。
         * 当手工修改了认证流程的任何配置，则默认配置（视图地址，权限校验等）失效。
         */
        // 配置登录页面
        http.formLogin() // 所有的登录认证相关配置
                .loginPage("/") // 具体的登录页面请求地址
                .loginProcessingUrl("/def") // 登录请求的具体地址。是可以不存在的映射（没有自定义控制器方法）。 一定对应post请求。
                .usernameParameter("uname") // 自定义登录请求的用户名请求参数
                .passwordParameter("pswd") // 自定义登录请求的密码请求参数
                //.defaultSuccessUrl("/main") // 登录成功后的默认跳转页面。不配置，重定向到上一次的访问地址。配置后重定向到指定地址。 只在直接访问登录页面时生效。
                //.successForwardUrl("/main") // 登录成功后的跳转页面。请求转发，一定生效。不推荐配置。
                .successHandler(new MyAuthenticationSuccessHandler("/main")) // 设置认证成功后的处理对象。
                // .failureUrl("/loginError"); // 登录失败后的跳转地址。
                // .failureForwardUrl("/loginError"); // 登录失败后的跳转地址。
                .failureHandler(new MyAuthenticationFailureHandler("/loginError")); // 设置认证失败后的处理对象

        /*
         * 自定义权限配置后，Security只根据配置来校验权限。
         */
        // 配置权限校验
        http.authorizeRequests() // 所有的权限相关配置
                .antMatchers("/", "/login", "/loginError") // 匹配哪些路径地址
                    .permitAll() // 对应匹配的地址的访问权限是什么， permitAll代表可以随意访问。
                .anyRequest() // 代表任意请求地址。相当于 antMatchers("/**")
                    .authenticated();  // 必须登录认证后，才能访问。未登录，跳转到登录页面。

        // 关闭csrf,想不考虑含义和功能。
        http.csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
