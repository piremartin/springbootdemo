package com.chj.springbootdemo.web.rest.vm;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVM {

    @Size(max = 10, message = "长度不能超过10")
    @NotBlank
    private String name;

    @Min(value = 1)
    @NotNull
    private Integer age;


    private String startTime;
    private String endTime;
    private int page = 0;
    private int size = 20;
}
