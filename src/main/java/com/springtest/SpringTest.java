package com.springtest;

import com.springtest.bean.MyDao;
import com.springtest.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MyDao myDao = (MyDao)context.getBean("myDao");
        MyDao myService = (MyDao)context.getBean("myService");
        System.out.println(myService.toString());
    }

}
