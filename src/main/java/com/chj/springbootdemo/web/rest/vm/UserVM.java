package com.chj.springbootdemo.web.rest.vm;

import lombok.Data;

@Data
public class UserVM {

    private Long id;

    private String name;
    private Integer age;
    private String addr;

}
