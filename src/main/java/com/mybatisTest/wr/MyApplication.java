package com.mybatisTest.wr;

import com.mybatis.MapperProxyFactory;

public class MyApplication {

    public static void main(String[] args) {
        UserMapper userMapper= MapperProxyFactory.getMapper(UserMapper.class);
       // List<User> list = userMapper.selectUser("zhangsan");
        User userById=userMapper.selectById(1);
        System.out.println(userById);

    }
}
