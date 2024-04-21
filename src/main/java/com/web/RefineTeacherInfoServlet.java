package com.web;


import com.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RefineTeacherInfoServlet")
public class RefineTeacherInfoServlet extends HttpServlet {

    private TeacherService teacherService=new TeacherService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String username = (String) session.getAttribute("username");


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        String introduction = request.getParameter("introduction");
        teacherService.RefineTeacherInfo(name,email,qq,introduction,username);
        //进一个页面
        //可以看自己的课程,
        //然后有一个按键可以增加课程,
    }
}
