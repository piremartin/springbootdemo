package com.chj.springbootdemo.service.impl;

import com.chj.springbootdemo.service.MyService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chehaojie
 * @date 2019/03/12 21:53
 */
@Component
public class MyServiceImpl implements MyService {
    private Map<Long, String> map = new HashMap<>();

    public MyServiceImpl(){
        map.put(1L, "aaa");
        map.put(2L, "bbb");
    }

    @Override
    public String sayHello() {
        return "hello";
    }

    //    @PostConstruct
//    public void init() {
//        map.put(1L, "aaa");
//        map.put(2L, "bbb");
//    }

    @Override
    public String getPwById(Long id) {
        return map.get(id);
    }
}
