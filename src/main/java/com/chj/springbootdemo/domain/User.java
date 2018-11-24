package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    public User(){}

    public User(String name){
        this.name = name;
    }

}