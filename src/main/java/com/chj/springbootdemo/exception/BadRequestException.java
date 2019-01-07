package com.chj.springbootdemo.exception;


/**
 * @author chehaojie
 */
public class BadRequestException extends BaseException{

    public BadRequestException(){
        super(10400, "入参不合法");
    }
}
