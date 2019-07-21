package com.mp.dao;

import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

//可以在spring环境下测试
@RunWith(SpringRunner.class)
//运行基于springboot的测试
@SpringBootTest
public class InsertTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("刘华");
        user.setAge(29);
        user.setEmail("xn@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数" + rows);
    }

}
