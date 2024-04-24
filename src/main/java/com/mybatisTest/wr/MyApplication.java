package com.mybatisTest.wr;

import com.mybatis.MapperProxyFactory;

import java.util.List;

public class MyApplication {

    public static void main(String[] args) {
        UserMapper userMapper= MapperProxyFactory.getMapper(UserMapper.class);
       // List<User> list = userMapper.selectUser("zhangsan");
         //userMapper.add("wr", null);
//userMapper.update("wr","111");
        User user = userMapper.selectById(1);
        System.out.println(user);
        // userMapper.deleteByUsername("wr");
        //User userById=userMapper.selectById(1);
       // System.out.println(list);

    }
}
