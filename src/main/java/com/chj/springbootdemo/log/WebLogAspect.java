package com.chj.springbootdemo.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Aspect
public class WebLogAspect {

    @Pointcut("execution(* com.chj.springbootdemo..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(){
        System.out.println("---dobefore");
    }
}
