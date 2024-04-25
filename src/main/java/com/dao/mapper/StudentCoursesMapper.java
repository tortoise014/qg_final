package com.dao.mapper;


import com.dao.pojo.StudentCourses;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;

import java.util.List;

public interface StudentCoursesMapper {
    @Select("select*from student_courses where course_id=#{course_id}")
    List<StudentCourses> selectByCourseId(@Param("course_id") Integer course_id);
    @Select("select*from student_courses where student_id=#{student_id}")
    List<StudentCourses> selectByStudentId(@Param("student_id") Integer student_id);
    }
