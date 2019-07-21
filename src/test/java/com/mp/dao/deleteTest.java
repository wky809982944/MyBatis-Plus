package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class deleteTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testDeleteById(){
        int rows = userMapper.deleteById(1152546309031444482L);
        System.out.println("删除条数" + rows);
    }

    @Test
    public void testDeleteByMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "向后");
        columnMap.put("age", 25);
        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("删除条数"+rows);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeleteBatchIds(){
        int rows = userMapper.deleteBatchIds(
                Arrays.asList(1152546309031444482L
                        , 1152545936086495233L
                        , 1152545452323856385L));
        System.out.println("删除记录数"+rows);
    }

    @Test
    public void testDeleteByWrapper(){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.eq(User::getAge, 27)
                   .or()
                   .gt(User::getAge, 41);
        int rows = userMapper.delete(lambdaQuery);
        System.out.println("rows = " + rows);
    }
}
