package com.mybatis;

import com.DemoApplication;
import com.mybatis.dao.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MybatisServiceTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {
        String poemName = testMapper.getPoemName();
        System.out.println(poemName);
    }
}