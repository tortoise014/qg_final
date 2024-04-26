package com.dao.mapper;

import com.dao.pojo.Student;

import com.mybatis.anation.Insert;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;
import com.mybatis.anation.Update;

import java.util.List;

public interface StudentMapper {
    @Select("select *from students where username=#{username} and password =#{password}")
    Student select(@Param("username") String username, @Param("password") String password);

    @Select("select *from students where Student_id=#{Student_id}")
    Student selectByStudent_id(@Param("Student_id") Integer Student_id);



    @Select("select *from students where username=#{username}")
    Student selectByStudentUsername(@Param("username") String username);

    @Insert("insert INTO students (username, password,name,student_id,grade,introduction) VALUES (#{username}, #{password},#{name},#{student_id},#{grade},#{introduction})")
    void addStudent(@Param("username") String username,@Param("password") String password,@Param("name") String name, @Param("student_id") Integer student_id, @Param("grade") Integer grade,
                    @Param("introduction") String introduction);

    @Update("UPDATE students SET name = #{name}, student_id = #{student_id}, grade = #{grade}, introduction = #{introduction} WHERE username = #{username}")
    void UpdateStuInfo(@Param("name") String name, @Param("student_id") Integer student_id, @Param("grade") Integer grade,
                       @Param("introduction") String introduction, @Param("username") String username);

}
