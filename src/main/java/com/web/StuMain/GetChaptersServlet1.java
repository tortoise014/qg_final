package com.web.StuMain;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.ChapterMapper;
import com.dao.pojo.Chapter;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getChaptersServlet1")
public class GetChaptersServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer course_id = Integer.valueOf(request.getParameter("courseId"));
        ChapterMapper chapterMapper = MapperProxyFactory.getMapper(ChapterMapper.class);
        List<Chapter> chapters = chapterMapper.selectAllByCourseId(course_id);
        String jsonString = JSON.toJSONString(chapters);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
