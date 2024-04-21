package com.web;

import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;
import sun.security.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");


        if ("student".equals(identity)){
            Student student=new Student();
            student.setUsername(username);
            student.setPassword(password);
           StudentMapper studentMapper=MapperProxyFactory.getMapper(StudentMapper.class);
            Student student1 = studentMapper.selectByStudentName(username);

            if(student1==null){
                response.setContentType("text/html;charset=utf-8");
                System.out.println("没找到可以加");
                studentMapper.addStudent(username,password);
            }else{
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("学生用户名已存在");
            }

        }else{
            Teacher teacher=new Teacher();
            teacher.setUsername(username);
            teacher.setPassword(password);
            TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
            Teacher t = teacherMapper.selectByTeacherName(username);
            if(t==null){
                teacherMapper.addTeacher(username,password);
            }else{
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("用户名已存在");
            }

        }
    }
}

