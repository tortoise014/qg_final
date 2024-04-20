package com.dao.mapper;

import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.Insert;
import com.mybatis.Param;
import com.mybatis.Select;

public interface TeacherMapper {
    @Select("select *from teachers where username=#{username} and password =#{password}")
    Teacher select(@Param("username") String username, @Param("password") String password);

    @Select("select *from students where username=#{username}")
    Teacher selectByTeacherName(@Param("username") String username);

    @Insert("insert INTO teachers (username, password) VALUES (#{username}, #{password})")
    void add(@Param("username") String username,@Param("password") String password);

}
