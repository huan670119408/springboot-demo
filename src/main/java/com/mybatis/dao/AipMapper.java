package com.mybatis.dao;

import com.mybatis.bean.Aip;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiBingyi on 2019/7/10 15:23
 */
@Mapper
public interface AipMapper {

    void insertAip(Aip aip);

}