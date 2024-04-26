package com.mybatisTest.wr;

import com.dao.mapper.QuestionMapper;
import com.dao.pojo.Questions;
import com.mybatis.MapperProxyFactory;

import java.util.List;

public class MyApplication {

    public static void main(String[] args) {
        QuestionMapper questionMapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        List<Questions> questions = questionMapper.selectByChapterId(16);
        System.out.println(questions);
        for (Questions question : questions) {
            String correct_answer = question.getCorrect_answer();
            System.out.println(correct_answer);
        }
       // UserMapper userMapper= MapperProxyFactory.getMapper(UserMapper.class);
       // List<User> list = userMapper.selectUser("zhangsan");
         //userMapper.add("wr", null);
//userMapper.update("wr","111");
       // User user = userMapper.selectById(1);
       // System.out.println(user);
        // userMapper.deleteByUsername("wr");
        //User userById=userMapper.selectById(1);
       // System.out.println(list);
       /* QuestionMapper mapper = MapperProxyFactory.getMapper(QuestionMapper.class);
        mapper.Insert(16,"一加一","二");
        System.out.println("11");*/
    }
}
