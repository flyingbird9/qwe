package com.bjsxt.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义的密码加密处理类型
 */
//@Component
public class MyPasswordEncoder implements PasswordEncoder {
    /**
     * 加密。参数是客户端传递过来的未加密的明文密码。
     * @param rawPassword
     * @return 加密后的密文密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        System.out.println("加密请求参数中的密码，明文是：" + rawPassword.toString());
        return rawPassword.toString();
    }

    /**
     * 校验。
     * @param rawPassword 客户端传递过来的未加密的明文密码
     * @param encodedPassword 数据库中查询到的，已加密的密文密码。 loadUserByUsername方法返回值中附带的密码。
     * @return true 密码校验成功；  false密码校验失败
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("校验密码是否正确，明文是：" + rawPassword + " ， 密文是：" + encodedPassword);
        return encode(rawPassword).equals(encodedPassword);
    }
}
