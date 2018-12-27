package com.chj.springbootdemo.plain;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author chehaojie
 */
public class Test1 {
    public static void main(String[] args) {
        ZonedDateTime zNow = ZonedDateTime.now();
        Instant iNow = Instant.now();
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(iNow, ZoneId.systemDefault());
    }
}
