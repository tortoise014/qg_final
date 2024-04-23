package com.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;
import com.sun.org.apache.xpath.internal.SourceTreeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String identity = request.getParameter("identity");
        boolean exists = false;

        try {
            if ("student".equals(identity)) {
                // 学生
                StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
                Student student = studentMapper.selectByStudentName(username);
                exists = (student != null);
            }
            if("teacher".equals(identity)){
                // 老师
                TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
                Teacher teacher = teacherMapper.selectByTeacherName(username);
                exists = (teacher != null);
            }
        } catch (Exception e) {
            // 处理异常情况
            exists = false;
            System.out.println("ji");
            e.printStackTrace(); // 或者记录日志
        }

        // 构建 JSON 对象
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("exists", exists);

        // 返回 JSON 对象
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
