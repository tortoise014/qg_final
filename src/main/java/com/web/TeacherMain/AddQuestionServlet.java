package com.web.TeacherMain;

import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.QuestionMapper;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
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
        Integer chapter_id = jsonObject.getInteger("courseId");
        System.out.println(chapter_id);
        String question_text = jsonObject.getString("description");
        System.out.println(question_text);
        String correct_answer = jsonObject.getString("answer");

        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        questionMapper.Insert(chapter_id,question_text,correct_answer);

        response.getWriter().write("add successfully");

    }
}
