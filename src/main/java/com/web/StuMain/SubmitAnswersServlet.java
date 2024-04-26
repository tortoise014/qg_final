package com.web.StuMain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.mapper.AnswerRecordMapper;
import com.dao.mapper.QuestionMapper;
import com.dao.mapper.StudentMapper;
import com.dao.mapper.TeacherMapper;
import com.dao.pojo.Questions;
import com.dao.pojo.Student;
import com.dao.pojo.Teacher;
import com.mybatis.MapperProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/submitAnswersServlet")
public class SubmitAnswersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取username
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
        StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
        Student student = studentMapper.selectByStudentUsername(username);
        Integer student_id = student.getId();


        String score;
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
        Integer course_id = jsonObject.getInteger("courseId");
        String time = jsonObject.getString("time");
        JSONArray answersArray = jsonObject.getJSONArray("answers");
        String[] answers = new String[answersArray.size()];
        for (int i = 0; i < answersArray.size(); i++) {
            answers[i] = answersArray.getString(i);

        }
        AnswerRecordMapper answerRecordMapper = MapperProxyFactory.getMapper(AnswerRecordMapper.class);
        //拿到对应的正确答案
        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);

        List<Questions> questions = questionMapper.selectByChapterId(course_id);
        int i=0;
        for (Questions question : questions) {
            score="0";

            String correct_answer = question.getCorrect_answer();
            if(correct_answer.equals(answers[i])){
                score="100";
            answerRecordMapper.addRecords(student_id,course_id,answers[i],score,time,(i+1));
            }
            else{
                answerRecordMapper.addRecords(student_id,course_id,answers[i],score,time,(i+1));
            }
            i++;
        }



    }
}
