package com.web;


import com.dao.pojo.Student;

import com.dao.pojo.Teacher;
import com.service.StudentService;
import com.service.TeacherService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
            private TeacherService teacherService=new TeacherService();
            private StudentService studentService=new StudentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        String role = request.getParameter("role");
        if ("teacher".equals(role)) {
            Teacher teacher = null;
            teacher = teacherService.loginTeacher(username, password);
            System.out.println("teacher:"+teacher);
            System.out.println("111");
            //获取对应字符输出流
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                if (teacher != null) {
                    //登陆成功

                    if("1".equals(rememberMe)){
                        //创建cookie
                        Cookie c_username=new Cookie("username",username);
                        Cookie c_password=new Cookie("password",password);
                        //发宋
                        c_username.setMaxAge(60*60*24*7); // 设置 Cookie 的过期时间为 1 小时
                        c_password.setMaxAge(60*60*24*7); // 设置 Cookie 的过期时间为 1 小时

                        response.addCookie(c_password); // 将 Cookie 添加到 HTTP 响应中，发送给客户端
                        response.addCookie(c_username); // 将 Cookie 添加到 HTTP 响应中，发送给客户端     }
                        HttpSession session = request.getSession(false); // 获取当前会话，如果不存在则返回 null
                        if (session != null) {
                            session.invalidate(); // 销毁当前会话
                        }

                        session = request.getSession(true); // 创建新的会话
                        session.setAttribute("username", username); // 存储新的用户名
                        session.setAttribute("password", password); // 存储新的密码
                        session.setAttribute("role", role); // 存储新的角色信息


                    //到老师主页面
                        request.getRequestDispatcher("/TeacherMain.html").forward(request,response);
                        }else{
                        HttpSession session = request.getSession(false); // 获取当前会话，如果不存在则返回 null
                        if (session != null) {
                            session.invalidate(); // 销毁当前会话
                        }

                        session = request.getSession(true); // 创建新的会话
                        session.setAttribute("username", username); // 存储新的用户名
                        session.setAttribute("password", password); // 存储新的密码
                        session.setAttribute("role", role); // 存储新的角色信息

                        request.getRequestDispatcher("/TeacherMain.html").forward(request,response);
                    }
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
            System.out.println("333");
            Student student=null;
            student = studentService.loginStudent(username, password);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                if (student != null) {
                    //登陆成功
                    if("1".equals(rememberMe)){
                        //创建cookie
                        Cookie c_username=new Cookie("username",username);
                        Cookie c_password=new Cookie("password",password);
                        //发宋
                        c_username.setMaxAge(60*60*24*7); // 设置 Cookie 的过期时间为 1 小时
                        c_password.setMaxAge(60*60*24*7); // 设置 Cookie 的过期时间为 1 小时

                        response.addCookie(c_password); // 将 Cookie 添加到 HTTP 响应中，发送给客户端
                        response.addCookie(c_username); // 将 Cookie 添加到 HTTP 响应中，发送给客户端     }
                        HttpSession session = request.getSession(false); // 获取当前会话，如果不存在则返回 null
                        if (session != null) {
                            session.invalidate(); // 销毁当前会话
                        }

                        session = request.getSession(true); // 创建新的会话
                        session.setAttribute("username", username); // 存储新的用户名
                        session.setAttribute("password", password); // 存储新的密码
                        session.setAttribute("role", role); // 存储新的角色信息

                        request.getRequestDispatcher("/StudentMain.html").forward(request,response);

                        System.out.println("444");
                        //到学生主页面
                    }else {
                        HttpSession session = request.getSession(false); // 获取当前会话，如果不存在则返回 null
                        if (session != null) {
                            session.invalidate(); // 销毁当前会话
                        }

                        session = request.getSession(true); // 创建新的会话
                        session.setAttribute("username", username); // 存储新的用户名
                        session.setAttribute("password", password); // 存储新的密码
                        session.setAttribute("role", role); // 存储新的角色信息


                        //到学生主页面
                        request.getRequestDispatcher("/StudentMain.html").forward(request, response);
                    }
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

