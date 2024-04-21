package com.web;

import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Student;
import com.mybatis.MapperProxyFactory;
import com.dao.pojo.Teacher;
import com.service.StudentService;
import com.service.TeacherService;
import com.sun.javafx.image.BytePixelSetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
            private TeacherService teacherService=new TeacherService();
            private StudentService studentService=new StudentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        if ("teacher".equals(role)) {
            Teacher teacher = null;
            teacher = teacherService.loginTeacher(username, password);
            System.out.println("Teachers: " + teacher);
            //获取对应字符输出流
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                if (teacher != null) {
                    //登陆成功
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    session.setAttribute("role",role);
                    writer.write("登陆成功");
                    String errorMsg = username;
                    String encodedErrorMsg = java.net.URLEncoder.encode(errorMsg, "UTF-8");
                    response.sendRedirect("/try2/StuInfoFrom.html?studentName=" + encodedErrorMsg);
                    writer.write("<script>setTimeout(function() { window.location.href = 'StuInfoFrom.html'; }, 3000);</script>");

                } else {
                    writer.write("登陆失败");
                    //登陆失败
                    String errorMsg = "用户名或者密码错误";
                    String encodedErrorMsg = java.net.URLEncoder.encode(errorMsg, "UTF-8");
                    response.sendRedirect("/try2/login.html?login_msg=" + encodedErrorMsg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Student student=null;
            student = studentService.loginStudent(username, password);

            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                if (student != null) {
                    //登陆成功
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    session.setAttribute("role",role);
                    writer.write("登陆成功");
                    String errorMsg = username;
                    String encodedErrorMsg = java.net.URLEncoder.encode(errorMsg, "UTF-8");
                    response.sendRedirect("/try2/StuInfoFrom.html?studentName=" + encodedErrorMsg);
                    //跳到完善信息
                    writer.write("<script>setTimeout(function() { window.location.href = 'StuInfoFrom.html'; }, 3000);</script>");
                } else {
                    writer.write("登陆失败");
                    //登陆失败

                    String errorMsg = "用户名或者密码错误";
                    String encodedErrorMsg = java.net.URLEncoder.encode(errorMsg, "UTF-8");
                    response.sendRedirect("/try2/login.html?login_msg=" + encodedErrorMsg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}

