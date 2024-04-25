package com.web;

import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        String name = request.getParameter("name");

        String introduction = request.getParameter("introduction");

        String identity = request.getParameter("identity");
        System.out.println("zhixlm");
        PrintWriter writer = response.getWriter();
        if ("student".equals(identity)){
            Integer student_id = Integer.valueOf(request.getParameter("student_id"));
            Integer grade = Integer.valueOf(request.getParameter("grade"));
            Student student=new Student();
            student.setUsername(username);
            student.setPassword(password);
            student.setName(name);
            student.setStudent_id(student_id);
            student.setGrade(grade);
            student.setIntroduction(introduction);
           StudentMapper studentMapper=MapperProxyFactory.getMapper(StudentMapper.class);
            Student student1 = studentMapper.selectByStudentName(username);
            System.out.println("zhixlm");
            if(student1==null){
                response.setContentType("text/html;charset=utf-8");
                System.out.println("没找到可以加");
                studentMapper.addStudent(username,password,name,student_id,grade,introduction);

                response.setContentType("text/html;charset=utf-8");

                request.getRequestDispatcher("/login.html").forward(request, response);
                //跳到完善信息

            }else{

                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("学生用户名已存在");
                request.getRequestDispatcher("/login.html").forward(request,response);
            }

        }else{
            String email = request.getParameter("email");
            String qq = request.getParameter("qq");
            Teacher teacher=new Teacher();
            teacher.setUsername(username);
            teacher.setPassword(password);
            TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
            Teacher t = teacherMapper.selectByTeacherName(username);
            if(t==null){
                response.setContentType("text/html;charset=utf-8");
                teacherMapper.addTeacher(username,password,name,email,qq,introduction);

                System.out.println("zhixlm");
                response.setContentType("text/html;charset=utf-8");

                request.getRequestDispatcher("/login.html").forward(request, response);


            }else{
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("用户名已存在");
                request.getRequestDispatcher("/login.html").forward(request,response);
            }

        }
    }
}

