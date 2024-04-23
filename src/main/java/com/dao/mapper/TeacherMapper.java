package com.dao.mapper;

import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.Insert;
import com.mybatis.Param;
import com.mybatis.Select;
import com.mybatis.Update;

public interface TeacherMapper {
    @Select("select *from teachers where username=#{username} and password =#{password}")
    Teacher select(@Param("username") String username, @Param("password") String password);

    @Select("select *from students where username=#{username}")
    Teacher selectByTeacherName(@Param("username") String username);

    @Insert("insert INTO teachers (username, password,name,email,qq,introduction) VALUES (#{username}, #{password},#{name},#{email}, #{qq}, #{introduction})")
    void addTeacher(@Param("username") String username,@Param("password") String password,@Param("name") String name, @Param("email") String email, @Param("qq") String qq,
                    @Param("introduction") String introduction);

    @Update("UPDATE teachers SET name = #{name}, email = #{email}, qq = #{qq}, introduction = #{introduction} WHERE username = #{username}")
    void RefineTeacherInfo(@Param("name") String name, @Param("email") String email, @Param("qq") String qq,
                       @Param("introduction") String introduction, @Param("username") String username);

}
