package com.springtest;

import com.mybatis.Config;
import com.mybatis.dao.TestMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by LiBingyi on 2019/7/10 18:23
 */
public class MybatisTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TestMapper testMapper = (TestMapper)context.getBean("testMapper");
        testMapper.getPoemName();
    }

}
