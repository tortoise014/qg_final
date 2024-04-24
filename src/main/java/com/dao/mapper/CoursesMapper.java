package com.dao.mapper;

import com.dao.pojo.Course;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;

import java.util.List;

public interface CoursesMapper {
    @Select("select *from courses where teacher_id=#{teacher_id}")
    List<Course> selectByTeacherId(@Param("teacher_id") Integer teacher_id);
}
