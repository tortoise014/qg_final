package com.web.TeacherMain;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.CoursesMapper;
import com.dao.mapper.StudentCoursesMapper;
import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Course;
import com.dao.pojo.Student;
import com.dao.pojo.StudentCourses;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getStudentsServlet")
public class GetStudentsServlet extends HttpServlet {
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
        TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.selectByTeacherName(username);
        Integer teacher_id = teacher.getId();
        System.out.println(teacher_id);
        CoursesMapper coursesMapper = MapperProxyFactory.getMapper(CoursesMapper.class);
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        // 获取课程信息
        List<Course> courses = coursesMapper.selectByTeacherId(teacher_id);
        System.out.println(courses);

        StudentCoursesMapper studentCoursesMapper = MapperProxyFactory.getMapper(StudentCoursesMapper.class);

// 组合学生和课程信息
        List<Map<String, Object>> courseStudentList = new ArrayList<>();

            for (Course course : courses) {
                Map<String, Object> courseStudent = new HashMap<>();
                courseStudent.put("course", course);
                Integer coursesId = course.getId();
                System.out.println(coursesId);
                List<Student> studentsInCourse = new ArrayList<>();

                List<StudentCourses> studentCourses = studentCoursesMapper.selectByCourseId(coursesId);
                if(studentCourses==null){
                    continue;
                }
                System.out.println(studentCourses);
                System.out.println("daozhe");
                for (StudentCourses studentCours : studentCourses) {

                    Integer student_id = studentCours.getStudent_id();
                    Student student = studentMapper.selectByStudent_id(student_id);
                    studentsInCourse.add(student);
                }
                courseStudent.put("students", studentsInCourse);
                courseStudentList.add(courseStudent);
            }




// 将组合后的对象转换为 JSON 格式
        String json = JSON.toJSONString(courseStudentList);
        System.out.println(json);
// 发送 JSON 格式的数据给前端
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
