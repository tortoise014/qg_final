package com.service;

import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

public class TeacherService {

    public Teacher loginTeacher(String username,String password){
        TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.select(username, password);
        return teacher;
    }
    public Teacher selectIdByTeacherUsername(String username){
        TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        Teacher teacher= teacherMapper.selectIdByTeacherName(username);
        return teacher;

    }

}
