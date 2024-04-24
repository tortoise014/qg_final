package com.mybatisTest.wr;

import com.mybatis.anation.Delete;
import com.mybatis.anation.Param;
import com.mybatis.anation.*;

import java.util.List;

public interface UserMapper {
    @Select("select *from tb_user where username=#{username}")
    public List<User> selectUser(@Param("username") String username);

    @Select("select * from tb_user where id=#{id}")
    User selectById(@Param("id")Integer id);
    @Select("select id from tb_user where id=#{id}")
    Integer selectIdById(@Param("id")Integer id);
@Insert("insert INTO tb_user (username, password) VALUES (#{username}, #{password})")
    void add(@Param("username") String username,@Param("password") String password);
    @Update("UPDATE tb_user SET username = #{username}, password = #{password} WHERE username = #{username}")
    void update(@Param("username") String username, @Param("password") String password);

    @Delete("DELETE FROM tb_user WHERE username = #{username}")
    void deleteByUsername(@Param("username") String username);
}