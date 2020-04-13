package com.baizhi;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YingxWujsApplicationTests {

     @Resource
     UserMapper userMapper;
    @Resource
    CategoryMapper categoryMapper;

//    @Test
//    public void contextLoads() {
//        Admin admin = adminDao.query("lis");
//        System.out.println("admin = " + admin);
//    }
    @Test
    public void test1() {
//        UserExample example = new UserExample();
//        example.createCriteria().andIdEqualTo("1");
//        List<User> users = userMapper.selectByExample(example);
//        System.out.println("----------------------");
//         users.forEach(user -> System.out.println(user));
        List<User> users = userMapper.selectAll();

        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void test2() {
        Integer integer = categoryMapper.selectRecords1("2");
        System.out.println("integer = " + integer);
    }
    @Test
    public void testexport(){

    }
    }
