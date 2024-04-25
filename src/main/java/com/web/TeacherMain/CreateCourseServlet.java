package com.web.TeacherMain;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.CoursesMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Course;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/createCourseServlet")
public class CreateCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
        }

        // 如果没有找到用户名或会话已过期，则处理登录过期的情况
        if (username == null) {
            // 处理登录过期，例如重定向到登录页面
            response.sendRedirect("/try2/login.html");
        }

        BufferedReader br=request.getReader();
        String params = br.readLine();
        Course course = JSON.parseObject(params, Course.class);
        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);
        TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.selectByTeacherName(username);
        Integer teacher_id = teacher.getId();
        String name = course.getName();
        String description = course.getDescription();
        String start_date = course.getStart_date();
        String end_date = course.getEnd_date();
        Integer capacity = course.getCapacity();
        coursesMapper.Insert(teacher_id,name,description,start_date,end_date,capacity);
        response.getWriter().write("courses create successfully");
    }
}
