package com.mybatis;

import com.mybatis.dao.PoemMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LiBingyi on 2019/7/19 14:16
 */
public class MybatisService {

    @Autowired
    private PoemMapper testMapper;

    public void test() {
        String poemName = testMapper.getPoemName("1");
        System.out.println(poemName);
    }

}
