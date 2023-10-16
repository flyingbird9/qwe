package com.bjsxt.security.impl;

import com.bjsxt.pojo.User;
import com.bjsxt.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 基于Security框架编写的认证实现
 * 必须实现接口： UserDetailsService
 * 重写接口中的唯一方法：  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
 *  返回值： UserDetails 接口，常用实现是Security框架内置的实现类型 User
 *  参数： username 登录用户的用户名
 *  异常： 如果用户名没有对应的数据，抛出此异常。
 */
@Component
public class UserLoginDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserLoginService userLoginService;

    public UserLoginDetailsServiceImpl(){
        System.out.println("自定义接口实现类型对象已创建");
    }

    /**
     * 认证方法。
     *
     *   流程：
     *     1. 客户端发送post请求，认证（登录）
     *     2. 客户端携带请求参数：用户名（username）和密码（password）
     *     3. Security直接调用UserDetailsService.loadUserByUsername方法，查询用户。（要求Spring容器中必须有此接口的实现对象。）
     *     4. 如果loadUserByUsername方法抛出异常，则直接提示客户端，用户名或密码错误。
     *     5. 如果loadUserByUsername方法返回了UserDetails类型的对象，则进入密码校验流程。
     *     6. 先使用PasswordEncoder加密请求发送来的密码，再校验请求参数密码和UserDetails中包含的密码是否一致。（要求容器必须有PasswordEncoder对象）
     *     7. 如果密码校验失败，直接提示客户端，用户名或密码错误
     *     8. 如果密码校验成功，进入认证成功后的视图。
     *
     *
     * AuthorityUtils - 权限工具。
     *   NO_AUTHORITIES - 空集合常量。
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername("+username+")方法开始执行");
        // 查询用户
        User user = userLoginService.login(username, null);
        System.out.println("查询的用户是：" + user);
        // 判断用户是否存在。如果存在，则返回Security内置的User对象，不存在则抛出异常。
        if(user == null){
            throw new UsernameNotFoundException(username+"用户不存在");
        }
        // 返回Security内置的User对象。
        return new org.springframework.security.core.userdetails.User(
                username, // 登录用户的用户名，也可以使用 user.getUsername()
                user.getPassword(), // 数据库中查询的密码
                AuthorityUtils.NO_AUTHORITIES  // 空的权限集合。今天不考虑权限问题。
        );
    }
}
