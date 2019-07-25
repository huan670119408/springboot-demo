package com.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.springtest.bean.MyImport;
import com.springtest.bean.MyImportSelector;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.springtest")
@Import({MyImport.class, MyImportSelector.class})
@MapperScan("com.mybatis.dao")
public class Config {

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new DruidDataSource();
        ((DruidDataSource) dataSource).setDriverClassName("com.mysql.jdbc.Driver");
        ((DruidDataSource) dataSource).setUrl("jdbc:mysql://192.168.80.131/poem_analysis");
        ((DruidDataSource) dataSource).setUsername("root");
        ((DruidDataSource) dataSource).setPassword("123456");
        ((DruidDataSource) dataSource).setInitialSize(10);
        ((DruidDataSource) dataSource).setMaxActive(100);
        ((DruidDataSource) dataSource).setMinIdle(5);
        ((DruidDataSource) dataSource).setMaxWait(60000);
        ((DruidDataSource) dataSource).setTestWhileIdle(true);
        ((DruidDataSource) dataSource).setTestOnBorrow(false);
        ((DruidDataSource) dataSource).setTestOnReturn(false);
        ((DruidDataSource) dataSource).setPoolPreparedStatements(false);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return factoryBean;
    }

}
