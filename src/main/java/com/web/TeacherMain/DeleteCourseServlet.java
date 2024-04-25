package com.web.TeacherMain;

import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.CoursesMapper;
import com.dao.pojo.Course;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(111);
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        // 解析 JSON 数据
        JSONObject json = JSONObject.parseObject(sb.toString());
        int courseId = json.getIntValue("courseId");
        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);

        coursesMapper.deleteCourseById(courseId);
        response.getWriter().write(" information delete successfully");
    }
}
