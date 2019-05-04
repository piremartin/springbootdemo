package com.chj.springbootdemo.demo.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * @author chehaojie
 * @date 2019/05/04 21:14
 */
public class RandomDemo1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Random random = new Random();
            int r = random.nextInt(9) + 1;
            String num = RandomStringUtils.randomNumeric(6);
            System.out.println("r = "+r+"---"+String.valueOf(r)+num);
        }
    }
}
