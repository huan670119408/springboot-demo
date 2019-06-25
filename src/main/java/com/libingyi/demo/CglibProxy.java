package com.libingyi.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by LiBingyi on 2019/6/25 17:47
 */
public class CglibProxy implements MethodInterceptor {

    public Object getProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("写在sayHello之前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("写在sayHello之后");
        return result;
    }

}
