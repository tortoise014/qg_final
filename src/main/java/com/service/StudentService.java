package com.service;

import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

public class StudentService {


    public Student loginStudent(String username, String password){
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        Student student = studentMapper.select(username, password);
        return student;

    }


    public void RefineStuInfo(String name,Integer student_id,String grande,String introduction,String username ){
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        studentMapper.UpdateStuInfo(name,student_id,grande,introduction,username);
        return;
    }
}
