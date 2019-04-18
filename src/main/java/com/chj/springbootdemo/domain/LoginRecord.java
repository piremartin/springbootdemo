package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

/**
 * 登录记录
 * @author chehaojie
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
