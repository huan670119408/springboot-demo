package com.spring;

import com.spring.bean.MyDao;
import com.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyDao myDao = (MyDao)context.getBean("myDao");
        myDao.printSomething("哈哈");
    }

}
