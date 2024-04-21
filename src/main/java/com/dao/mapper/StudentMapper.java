package com.dao.mapper;

import com.dao.pojo.Student;

import com.mybatis.Insert;
import com.mybatis.Param;
import com.mybatis.Select;
import com.mybatis.Update;

public interface StudentMapper {
    @Select("select *from students where username=#{username} and password =#{password}")
    Student select(@Param("username") String username, @Param("password") String password);


    @Select("select *from students where username=#{username}")
    Student selectByStudentName(@Param("username") String username);

    @Insert("insert INTO students (username, password) VALUES (#{username}, #{password})")
    void addStudent(@Param("username") String username,@Param("password") String password);

    @Update("UPDATE students SET name = #{name}, student_id = #{student_id}, grande = #{grande}, introduction = #{introduction} WHERE username = #{username}")
    void UpdateStuInfo(@Param("name") String name, @Param("student_id") Integer student_id, @Param("grande") String grande,
                       @Param("introduction") String introduction, @Param("username") String username);

}
