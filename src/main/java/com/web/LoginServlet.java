package com.web;

import com.dao.mapper.TeacherMapper;
import com.mybatis.MapperProxyFactory;
import com.dao.pojo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Teacher teacher=null;
        TeacherMapper teacherMapper= MapperProxyFactory.getMapper(TeacherMapper.class);
        System.out.println("执行了");
        teacher = teacherMapper.select(username, password);
        System.out.println("Teachers: " + teacher);
        //获取对应字符输出流
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        try {
            if(teacher!=null ){
                //登陆成功
                writer.write("登陆成功");
            }else {
                writer.write("登陆失败");
                //登陆失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}

