package com.dao.mapper;

import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.Insert;
import com.mybatis.Param;
import com.mybatis.Select;

public interface StudentMapper {
    @Select("select *from students where username=#{username} and password =#{password}")
    Student select(@Param("username") String username, @Param("password") String password);


    @Select("select *from students where username=#{username}")
    Student selectByStudentName(@Param("username") String username);

    @Insert("insert INTO students (username, password) VALUES (#{username}, #{password})")
    void addStudent(@Param("username") String username,@Param("password") String password);
}
