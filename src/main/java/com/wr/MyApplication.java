package com.wr;

import com.mybatis.MapperProxyFactory;

import java.util.List;

public class MyApplication {

    public static void main(String[] args) {
        UserMapper userMapper= MapperProxyFactory.getMapper(UserMapper.class);
       // List<User> list = userMapper.selectUser("zhangsan");
        User userById=userMapper.selectById(2);
        System.out.println(userById);

    }
}
