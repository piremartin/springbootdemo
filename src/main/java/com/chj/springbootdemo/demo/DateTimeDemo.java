package com.chj.springbootdemo.demo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author chehaojie
 * @date 2019/04/30 14:30
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println("time1:"+l);
        long l1 = System.currentTimeMillis() / 1000;
        System.out.println("time2:"+l1);
//        time1:1556605902
//        time2:1556605902
    }
}
