package com.web.StuMain;

import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.StudentCoursesMapper;
import com.dao.mapper.StudentMapper;
import com.dao.pojo.Student;
import com.dao.pojo.StudentCourses;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addSelectedCourseServlet")
public class AddSelectedCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//解析json
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
//拿username
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
        // 解析 JSON 数据
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        Student student = studentMapper.selectByStudentUsername(username);
        Integer student_id = student.getStudent_id();
        JSONObject json = JSONObject.parseObject(sb.toString());
        int course_id = json.getIntValue("courseId");
        StudentCoursesMapper studentCoursesMapper = MapperProxyFactory.getMapper(StudentCoursesMapper.class);
        StudentCourses select = studentCoursesMapper.select(course_id, student_id);
        if(select==null){
            studentCoursesMapper.addStudentCourse(student_id,course_id);
        }
        response.getWriter().write("success!");
        }
}
