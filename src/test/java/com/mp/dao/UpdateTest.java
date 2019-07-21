package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testUpdateById(){
        User user = new User();
//        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数："+rows);
    }

    @Test
    public void testUpdateByWrapper(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟");
        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);
        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数" + rows);
    }

    @Test
    public void testUpdateByWrapper1(){
        User whereUser = new User();
        whereUser.setName("李艺伟");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(whereUser);
        int rows = userMapper.update(whereUser, updateWrapper);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 只更新少量字段
     */
    @Test
    public void testUpdateByWrapper3(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟")
                     .eq("age", 29)
                     .set("age", 30);
        int rows = userMapper.update(null,updateWrapper);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 只更新少量字段
     * 利用Lambda
     */
    @Test
    public void testUpdateByWrapperLambda(){
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.<User>lambdaUpdate();
        lambdaUpdate.eq(User::getName, "李艺为")
                    .eq(User::getAge, 30)
                    .set(User::getAge, 31);
        int rows = userMapper.update(null,lambdaUpdate);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 只更新少量字段
     * 利用Lambda
     */
    @Test
    public void testUpdateByWrapperLambdaChain(){
        boolean update = new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName, "李艺为")
                .eq(User::getAge, 30)
                .set(User::getAge, 31)
                .update();

    }
}
