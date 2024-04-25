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
import java.util.List;

@WebServlet("/getChaptersServlet")
public class GetChaptersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 解析 JSON 数据

        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        System.out.println(courseId);
        ChapterMapper chapterMapper = MapperProxyFactory.getMapper(ChapterMapper.class);
        List<Chapter> chapters = chapterMapper.selectAllByCourseId(courseId);
        String jsonString = JSON.toJSONString(chapters);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
