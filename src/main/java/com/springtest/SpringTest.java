package com.springtest;

import com.springtest.bean.MyImport;
import com.springtest.bean.MyImportSelectorAAA;
import com.springtest.bean.MyImportSelectorBBB;
import com.springtest.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        MyDao myDao = (MyDao)context.getBean("myDao");
//        MyDao myService = (MyDao)context.getBean("myService");
//        System.out.println(myService.toString());
        MyImport myImport = (MyImport)context.getBean("myImport");
        myImport.printSomething();
        MyImportSelectorAAA myImportSelectorAAA = (MyImportSelectorAAA)context.getBean("myImportSelectorAAA");
        myImportSelectorAAA.printSomething();
        MyImportSelectorBBB myImportSelectorBBB = (MyImportSelectorBBB)context.getBean("myImportSelectorBBB");
        myImportSelectorBBB.printSomething();

    }

}
