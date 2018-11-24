package com.chj.springbootdemo.web.rest.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private String name;

    private Integer age;

    private LocalDateTime createTime;
}
