package com.chj.springbootdemo.service.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * @author chehaojie
 * @date 2019/05/04 21:19
 */
public class IdGeneratorUtil {

    public static Long randomId(int length) {
        Random random = new Random();
        int r = random.nextInt(9) + 1;
        String left = RandomStringUtils.randomNumeric(length - 1);
        return Long.valueOf(String.valueOf(r) + left);
    }
}
