package com.web;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/editTeacherInfoServlet")
public class EditTeacherInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("111");

        BufferedReader br=request.getReader();
        String params = br.readLine();
        Teacher teacher = JSON.parseObject(params, Teacher.class);
        TeacherMapper mapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        String username = teacher.getUsername();
        String password = teacher.getPassword();
        String name = teacher.getName();
        String email = teacher.getEmail();
        String introduction = teacher.getIntroduction();
        String qq = teacher.getQq();
        System.out.println("hhh");
        mapper.RefineTeacherInfo1(password,name,email,qq,introduction,username);
        System.out.println("gail");
        response.getWriter().write("User information updated successfully");

    }
}
