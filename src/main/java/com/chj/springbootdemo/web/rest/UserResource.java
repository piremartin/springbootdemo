package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Resource(name = "userService")
//    @Resource(type = UserService.class)
//    @Resource(type = UserServiceImpl.class)
    private UserService userService;

    @PostMapping("/save")
    public void save(){
        userService.save();
    }
}
