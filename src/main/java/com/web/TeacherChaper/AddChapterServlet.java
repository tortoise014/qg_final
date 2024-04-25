package com.web.TeacherChaper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.ChapterMapper;
import com.dao.pojo.Chapter;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addChapterServlet")
public class AddChapterServlet extends HttpServlet {
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

        // 解析 JSON 字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        // 提取所需的数据
        String courseId = jsonObject.getString("courseId");
        Integer course_id= Integer.valueOf(courseId);
        System.out.println(course_id);
        String title = jsonObject.getString("title");
        System.out.println(title);
        String content = jsonObject.getString("content");
        System.out.println(content);
        ChapterMapper chapterMapper = MapperProxyFactory.getMapper(ChapterMapper.class);
        chapterMapper.addChapter(course_id,title,content);
        response.getWriter().write("User information add successfully");
    }
}
