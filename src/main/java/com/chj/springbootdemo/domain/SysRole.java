package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author chehaojie
 * @date 2019/04/21 23:02
 */
@Data
@Entity
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 角色名称
     */
    private String name;
}
