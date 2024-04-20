package com.mybatisTest.wr;

import com.mybatis.Insert;
import com.mybatis.Param;
import com.mybatis.Select;

import java.util.List;

public interface UserMapper {
    @Select("select *from tb_user where username=#{username}")
    public List<User> selectUser(@Param("username") String username);

    @Select("select * from tb_user where id=#{id}")
    public User selectById(@Param("id")Integer id);
@Insert("insert INTO tb_user (username, password) VALUES (#{username}, #{password})")
    void add(@Param("username") String username,@Param("password") String password);

}