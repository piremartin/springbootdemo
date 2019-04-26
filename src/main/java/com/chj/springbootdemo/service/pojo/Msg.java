package com.chj.springbootdemo.service.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chehaojie
 * @date 2019/04/26 15:20
 */
@Getter
@Setter
public class Msg {

    private String title;
    private String content;
    private String etraInfo;

    public Msg(String title, String content, String etraInfo){
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
}
