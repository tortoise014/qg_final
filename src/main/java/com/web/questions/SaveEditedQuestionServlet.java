package com.web.questions;

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

@WebServlet("/saveEditedQuestionServlet")
public class SaveEditedQuestionServlet extends HttpServlet {
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
        Integer id = jsonObject.getInteger("id");
        System.out.println(id);
        String question_text = jsonObject.getString("question_text");
        System.out.println(question_text);
        String correct_answer = jsonObject.getString("correct_answer");
        System.out.println(correct_answer);
        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        questionMapper.UpdateInfo(question_text,correct_answer,id);
        response.getWriter().write(" information updated successfully");

    }
}
