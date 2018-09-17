package com.chj.springbootdemo.web.rest;

import lombok.Data;

@Data
public class PersonVM {

    private Long id;

    private String name;
    private Integer age;
    private String addr;

}
