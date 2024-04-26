package com.web;

import com.alibaba.fastjson.JSONObject;
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
import java.io.BufferedReader;
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

        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
            System.out.println("111");
        }
        String jsonString = jsonBuilder.toString();

        // 解析 JSON 字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        // 提取所需的数据


        String username = jsonObject.getString("username");
        String identity = jsonObject.getString("identity");

        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String introduction = jsonObject.getString("introduction");

        System.out.println(identity);
        System.out.println("zhixlm");
        PrintWriter writer = response.getWriter();
        if ("student".equals(identity)){
            Integer student_id = jsonObject.getInteger("student_id");
            Integer grade = jsonObject.getInteger("grade");

            Student student=new Student();
            student.setUsername(username);
            student.setPassword(password);
            student.setName(name);
            student.setStudent_id(student_id);
            student.setGrade(grade);
            student.setIntroduction(introduction);
           StudentMapper studentMapper=MapperProxyFactory.getMapper(StudentMapper.class);
            Student student1 = studentMapper.selectByStudentUsername(username);
            System.out.println("zhixlm");
            if(student1==null){
                response.setContentType("text/html;charset=utf-8");
                System.out.println("没找到可以加");
                studentMapper.addStudent(username,password,name,student_id,grade,introduction);
                response.getWriter().write("success");
                //跳到完善信息
            }else{
                response.setContentType("text/html;charset=utf-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("学生用户名已存在");
            }

        }else{
            String email = jsonObject.getString("email");
            String qq = jsonObject.getString("qq");
            Teacher teacher=new Teacher();
            teacher.setUsername(username);
            teacher.setPassword(password);
            TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
            Teacher t = teacherMapper.selectByTeacherName(username);
            if(t==null){
                response.setContentType("text/html;charset=utf-8");
                teacherMapper.addTeacher(username,password,name,email,qq,introduction);

                response.getWriter().write("success");

            }else{
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("用户名已存在");
                request.getRequestDispatcher("/login.html").forward(request,response);
            }

        }
    }
}

