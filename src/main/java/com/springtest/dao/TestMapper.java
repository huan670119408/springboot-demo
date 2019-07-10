package com.springtest.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiBingyi on 2019/7/10 15:23
 */
@Mapper
public interface TestMapper {

    String getPoemName();

}
