package com.web.StuMain;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.QuestionMapper;
import com.dao.mapper.StudentMapper;
import com.dao.mapper.StudentProgressMapper;
import com.dao.pojo.Questions;
import com.dao.pojo.Student;
import com.dao.pojo.StudentProgress;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/getQuestionsServlet1")
public class GetQuestionsServlet1 extends HttpServlet {
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
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        Student student = studentMapper.selectByStudentUsername(username);
        Integer student_id = student.getStudent_id();


        Integer course_id = Integer.valueOf(request.getParameter("courseId"));
        StudentProgressMapper studentProgressMapper = MapperProxyFactory.getMapper(StudentProgressMapper.class);
        StudentProgress studentProgress = studentProgressMapper.selectByStuIdCourseId(course_id, student_id);
        Integer attendance = studentProgress.getAttendance();
        attendance+=1;
        studentProgressMapper.UpdateStuProInfo(attendance,course_id,student_id);


        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        List<Questions> questions = questionMapper.selectByChapterId(course_id);
        String jsonString = JSON.toJSONString(questions);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
