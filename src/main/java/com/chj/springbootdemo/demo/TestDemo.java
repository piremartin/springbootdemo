package com.chj.springbootdemo.demo;

import com.chj.springbootdemo.demo.proxy.MyInvocationHandler;
import com.chj.springbootdemo.service.MyService;
import com.chj.springbootdemo.service.impl.MyServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author chehaojie
 * @date 2019/03/12 21:41
 */
public class TestDemo {

    public static void main(String[] args) {

        MyService myService = new MyServiceImpl();

        MyService us = (MyService)Proxy.newProxyInstance(myService.getClass().getClassLoader(),
                myService.getClass().getInterfaces(), new MyInvocationHandler(myService));


        String pwById = us.getPwById(1L);
        System.out.println(pwById);
    }
}
