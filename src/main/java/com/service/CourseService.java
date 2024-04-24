package com.service;

import com.dao.mapper.CoursesMapper;
import com.dao.pojo.Course;
import com.mybatis.MapperProxyFactory;

import java.util.List;

public class CourseService {



    public List<Course> getCourseInfo(int teacher_id){

        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);
        List<Course> courses = coursesMapper.selectByTeacherId(teacher_id);
        return courses;
    }
}
