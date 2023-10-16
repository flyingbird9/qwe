package com.bjsxt.service.impl;

import com.bjsxt.mapper.UserMapper;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * 增加了Security框架后，登录服务功能，只要查询用户对象即可。不需要判断用户是否存在，不需要判断密码是否正确。
     * Security框架有一套独立的认证逻辑。
     * 要求必须实现指定的接口，重写接口中的方法，完成认证逻辑。可以和当前的服务实现类型统一，不推荐。
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        // 判断用户是否存在
        // 判断密码是否正确
        // 返回
        return user;
    }
}
