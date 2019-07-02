package com.springtest.bean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by LiBingyi on 2019/6/26 10:11
 */
@EnableTransactionManagement
public class MyImport {

    public void printSomething() {
        System.out.println("MyImport");
    }

}
