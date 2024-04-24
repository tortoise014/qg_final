package com.dao.mapper;

import com.dao.pojo.Teacher;
import com.mybatis.anation.Insert;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;
import com.mybatis.anation.Update;

public interface TeacherMapper {
    @Select("select *from teachers where username=#{username} and password =#{password}")
    Teacher select(@Param("username") String username, @Param("password") String password);


    @Select("select *from teachers where username=#{username}")
    Teacher selectByTeacherName(@Param("username") String username);
    @Select("select * from teachers where username=#{username}")
     Teacher selectIdByTeacherName(@Param("username") String username);

    @Insert("insert INTO teachers (username, password,name,email,qq,introduction) VALUES (#{username}, #{password},#{name},#{email}, #{qq}, #{introduction})")
    void addTeacher(@Param("username") String username,@Param("password") String password,@Param("name") String name, @Param("email") String email, @Param("qq") String qq,
                    @Param("introduction") String introduction);

    @Update("UPDATE teachers SET name = #{name}, email = #{email}, qq = #{qq}, introduction = #{introduction} WHERE username = #{username}")
    void RefineTeacherInfo(@Param("name") String name, @Param("email") String email, @Param("qq") String qq,
                       @Param("introduction") String introduction, @Param("username") String username);
    @Update("UPDATE teachers SET password=#{password},name = #{name}, email = #{email}, qq = #{qq}, introduction = #{introduction} WHERE username = #{username}")
    void RefineTeacherInfo1(@Param("password") String password,@Param("name") String name, @Param("email") String email, @Param("qq") String qq,
                           @Param("introduction") String introduction, @Param("username") String username);

}
