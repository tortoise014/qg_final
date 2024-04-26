package com.web.StuMain;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.CoursesMapper;
import com.dao.pojo.Course;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/getAllCoursesServlet")
public class GetAllCoursesServlet extends HttpServlet {
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
        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);
        List<Course> courses = coursesMapper.selectAll();


        String jsonString = JSON.toJSONString(courses);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
