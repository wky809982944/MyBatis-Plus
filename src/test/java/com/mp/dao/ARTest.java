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
public class ARTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张草");
        user.setAge(24);
        user.setEmail("xn@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean insert = user.insert();
        System.out.println(insert);
    }
    @Test
    public void testSelectById(){
        User user = new User();
        User userSelect = user.selectById(1152911029672353793L);
        System.out.println("userSelect = " + userSelect);
    }

    @Test
    public void testSelectById2(){
        User user = new User();
        user.setId(1152911029672353793L);
        User userSelect = user.selectById();
        System.out.println("userSelect = " + userSelect);
    }
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1152911029672353793L);
        user.setName("张草草");
        boolean updateById = user.updateById();
        System.out.println("updateById = " + updateById);
    }

    @Test
    public void testDeleteById(){
        User user = new User();
        user.setId(1152911029672353793L);
        user.setName("张草草");
        boolean b = user.deleteById();
        System.out.println("b = " + b);
    }
    @Test
    public void insertOrUpdate(){
        User user = new User();
//        插入要设置id
        user.setName("张强");
        user.setEmail("zq@baomidou.com");
        user.setManagerId(1088248166370832385L);
        boolean insertOrUpdate = user.insertOrUpdate();
        System.out.println("insertOrUpdate = " + insertOrUpdate);
    }
}
