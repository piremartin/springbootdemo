package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

/**
 * 登录记录
 * @author chehaojie
 * @date 2019-4-18 11:44:28
 */
@Data
@Entity
@Table(name = "login_record")
public class LoginRecord {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "login_time")
    private Instant loginTime;


}
