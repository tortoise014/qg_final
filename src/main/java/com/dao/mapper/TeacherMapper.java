package com.dao.mapper;

import com.dao.pojo.Teacher;
import com.mybatis.Param;
import com.mybatis.Select;

public interface TeacherMapper {
    @Select("select *from teachers where username=#{username} and password =#{password}")
    Teacher select(@Param("username") String username, @Param("password") String password);
}
