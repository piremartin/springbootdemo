package com.chj.springbootdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chehaojie
 */
@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private Integer code;
    private String msg;
}
