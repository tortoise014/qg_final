package com.dao.mapper;


import com.dao.pojo.StudentProgress;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;
import com.mybatis.anation.Update;

import java.util.List;

public interface StudentProgressMapper {
    @Select("select *from student_progress where student_id=#{student_id}")
    List<StudentProgress> selectByStuId(@Param("student_id") Integer student_id);

    @Select("select *from student_progress where student_id=#{student_id} and course_id=#{course_id}")
    StudentProgress selectByStuIdCourseId(@Param("course_id") Integer course_id,@Param("student_id") Integer student_id);

    @Update("UPDATE student_progress SET attendance = #{attendance} WHERE student_id=#{student_id} and course_id=#{course_id}")
    void UpdateStuProInfo( @Param("attendance") Integer attendance,@Param("course_id") Integer course_id,@Param("student_id") Integer student_id);
}
