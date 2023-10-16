package com.bjsxt.mapper;

import com.bjsxt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 使用注解编写SQL
 */
@Mapper
public interface UserMapper {
    @Select("select id, username, password from user where username = #{username}")
    User selectByUsername(String username);
}
