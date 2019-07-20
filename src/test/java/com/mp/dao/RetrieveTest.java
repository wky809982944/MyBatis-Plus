package com.mp.dao;

import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//可以在spring环境下测试
@RunWith(SpringRunner.class)
//运行基于springboot的测试
@SpringBootTest
public class RetrieveTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println("user = " + user);
    }
    @Test
    public void testSelectIds(){
        List<Long> idList = Arrays.asList(1094592041087729666L
                , 1088248166370832385L
                , 1152541720534183937L);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }
    @Test
    public void testSelectByMap(){
//       map.put("name","王天风")
//        map.put("age",30)
//        where name="王天风" and age =30
        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name", "王天风");
//        age 数据库列名不是属性名
        columnMap.put("age", 27);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }
}
