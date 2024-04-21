package com.web;

import com.mybatis.Update;
import com.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/refineStuInfoServlet")
public class RefineStuInfoServlet extends HttpServlet {
    private StudentService studentService=new StudentService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String username = (String) session.getAttribute("username");


        // 获取前端表单提交的数据
        String name = request.getParameter("name");
        Integer student_id = Integer.parseInt(request.getParameter("student_id"));
        String grade = request.getParameter("grade");
        String introduction = request.getParameter("introduction");

        studentService.RefineStuInfo(name,student_id,grade,introduction,username);
        //进入选课页面

    }
}
