package com.mybatisTest.wr;

import com.mybatis.MapperProxyFactory;

import java.util.List;

public class MyApplication {

    public static void main(String[] args) {
        UserMapper userMapper= MapperProxyFactory.getMapper(UserMapper.class);
       // List<User> list = userMapper.selectUser("zhangsan");
         userMapper.add("wr", "111");
        //User userById=userMapper.selectById(1);
       // System.out.println(list);

    }
}
