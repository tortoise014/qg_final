package com.web.TeacherMain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.AnswerRecordMapper;
import com.dao.mapper.CoursesMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.AnswerRecords;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getStudentRecordsServlet")
public class GetStudentRecordsServlet extends HttpServlet {
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
        AnswerRecordMapper answerRecordMapper = MapperProxyFactory.getMapper(AnswerRecordMapper.class);

        TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.selectByTeacherName(username);
        Integer teacher_id = teacher.getId();
        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);
        List<Course> courses = coursesMapper.selectByTeacherId(teacher_id);
        List<AnswerRecords> allAnswerRecords = new ArrayList<>(); // 创建一个新列表来存储所有答题记录
        for (Course course : courses) {
            Integer course_id = course.getId();
            List<AnswerRecords> answerRecords = answerRecordMapper.selectByCourseId(course_id);
            if(answerRecords!=null){
                allAnswerRecords.addAll(answerRecords);
            }
        }

        String jsonString = JSON.toJSONString(allAnswerRecords);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
