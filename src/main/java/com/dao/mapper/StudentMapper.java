package com.dao.mapper;

import com.dao.pojo.Student;

import com.mybatis.anation.Insert;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;
import com.mybatis.anation.Update;

public interface StudentMapper {
    @Select("select *from students where username=#{username} and password =#{password}")
    Student select(@Param("username") String username, @Param("password") String password);


    @Select("select *from students where username=#{username}")
    Student selectByStudentName(@Param("username") String username);

    @Insert("insert INTO students (username, password,name,student_id,grande,introduction) VALUES (#{username}, #{password},#{name},#{student_id},#{grande},#{introduction})")
    void addStudent(@Param("username") String username,@Param("password") String password,@Param("name") String name, @Param("student_id") Integer student_id, @Param("grande") Integer grande,
                    @Param("introduction") String introduction);

    @Update("UPDATE students SET name = #{name}, student_id = #{student_id}, grande = #{grande}, introduction = #{introduction} WHERE username = #{username}")
    void UpdateStuInfo(@Param("name") String name, @Param("student_id") Integer student_id, @Param("grande") Integer grande,
                       @Param("introduction") String introduction, @Param("username") String username);

}
