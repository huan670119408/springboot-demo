package com.springtest;

import com.springtest.config.Config;
import com.springtest.dao.TestMapper;
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
