package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 * @author chehaojie
 * @date 2019-4-18 11:47:39
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public User(){

    }
    public User(String name){
        this.name = name;
    }


}
