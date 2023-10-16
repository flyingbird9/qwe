package com.bjsxt.service;

import com.bjsxt.pojo.User;

/**
 * 用户认证服务接口
 */
public interface UserLoginService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
