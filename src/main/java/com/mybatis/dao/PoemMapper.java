package com.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiBingyi on 2019/7/10 15:23
 */
@Mapper
public interface PoemMapper {

    String getPoemName(String str);

}