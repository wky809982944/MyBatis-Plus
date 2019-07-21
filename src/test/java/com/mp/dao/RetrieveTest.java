package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /**
     * 1.名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void testSelectByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.like("name", "雨")
                    .lt("age", 40);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 1.名字中包含雨并且20<=年龄<=40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void testSelectByWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨")
                    .between("age", 20, 40)
                    .isNotNull("email");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 3.名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age >=25 order by age desc, id asc
     */
    @Test
    public void testSelectByWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                    .or()
                    .ge("age", 25)
                    .orderByDesc("age")
                    .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 4、创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from where user name like '王%')
     */
    @Test
    public void testSelectByWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                    .inSql("manager_id", "select id from user where name like '王%'");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 5.名字威王姓并且（年龄<40或邮箱不为空）
     * name like'王%' and (age < 40 or email is not null)
    */
    @Test
    public void testSelectByWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                    .and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 6.名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age > 20) and email is not null
     */
    @Test
    public void testSelectByWrapper6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                    .or(wq -> wq.lt("age", 40)
                                .gt("age", 20)
                                .isNotNull("email"));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }
    /**
     * 7.（年龄小于40或邮箱不为空）并且名字为王姓
     * (age < 40 or email is not null ) and name like '王%'
     */
    @Test
    public void testSelectByWrapper7(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40)
                                    .or()
                                    .isNotNull("email"))
                    .likeRight("name", "王");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 8.年龄威30、31、34、 35
     * age in (30、 31 、 34 、 35)
     */
    @Test
    public void testSelectByWrapper8(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * 9 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void testSelectByWrapper9(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }
}
