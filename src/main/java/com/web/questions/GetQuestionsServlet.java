package com.web.questions;

import com.alibaba.fastjson.JSON;
import com.dao.mapper.QuestionMapper;
import com.dao.pojo.Course;
import com.dao.pojo.Questions;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getQuestionsServlet")
public class GetQuestionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        System.out.println(courseId);
        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        List<Questions> questions = questionMapper.selectByChapterId(courseId);
        String jsonString = JSON.toJSONString(questions);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
