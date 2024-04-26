package com.web.StuMain;

import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.StudentMapper;
import com.dao.pojo.Student;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/saveUserInfoServlet")
public class SaveUserInfoServlet extends HttpServlet {
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
//回去usernasme
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

        // 解析 JSON 字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        // 提取所需的数据
        Integer id = jsonObject.getInteger("id");
        Integer grade = jsonObject.getInteger("grade");
        Integer student_id = jsonObject.getInteger("student_id");

        String introduction = jsonObject.getString("introduction");
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        studentMapper.UpdateStuInfo(name,student_id,grade,introduction,username);
        response.getWriter().write("User information updated successfully");

    }
}
