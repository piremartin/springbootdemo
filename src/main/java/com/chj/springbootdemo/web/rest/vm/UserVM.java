package com.chj.springbootdemo.web.rest.vm;

import lombok.Data;

@Data
public class UserVM {

    private Long id;
    private String name;

    private Integer age;


    private String startTime;
    private String endTime;
    private int page;
    private int size;
}
