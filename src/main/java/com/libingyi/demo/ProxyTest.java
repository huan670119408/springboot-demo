package com.libingyi.demo;

public class ProxyTest {

    public static void main(String[] args) {
        TestServiceImpl t = new TestServiceImpl();
        CglibProxy cglibProxy = new CglibProxy();
        TestServiceImpl proxyInstance = (TestServiceImpl)cglibProxy.getProxyInstance(t);
        proxyInstance.saySomething("hello");
    }

}
