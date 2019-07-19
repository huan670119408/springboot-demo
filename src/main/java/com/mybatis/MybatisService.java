package com.mybatis;

import com.mybatis.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LiBingyi on 2019/7/19 14:16
 */
public class MybatisService {

    @Autowired
    private TestMapper testMapper;

    public void test(){
        String poemName = testMapper.getPoemName();
        System.out.println(poemName);
}

}
