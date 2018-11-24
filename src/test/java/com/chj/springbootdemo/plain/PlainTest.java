package com.chj.springbootdemo.plain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chj.springbootdemo.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PlainTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("王力宏");
        user.setAge(18);
    }

    @Test
    public void testFastJson1() {
        Date date = new Date();
        String strDate = JSON.toJSONString(date);
        System.out.println("strDate=" + strDate);
    }
    @Test
    public void test2() {
        User user = new User();
        user.setName("leehome");
        user.setAge(18);
        String jsonPerson = JSON.toJSONString(user);
        User parseUser = JSON.parseObject(jsonPerson, User.class);
        System.out.println("------");
    }
    @Test
    public void test3(){
        List<User> list = new ArrayList<>();
        list.add(new User("zhoujielun"));
        list.add(new User("linjunjie"));
        String jsonList = JSON.toJSONString(list);
        List<User> parseList = JSON.parseArray(jsonList, User.class);
        System.out.println("-----------------");
    }
    @Test
    public void test4(){
        Map<Long,User> map = new HashMap<>();
        map.put(1L,new User("林志玲"));
        map.put(2L,new User("林俊杰"));
        String jsonMap = JSON.toJSONString(map) ;

        Map<Long, User> parseMap = JSON.parseObject(jsonMap,
                new TypeReference<Map<Long, User>>() {});
        System.out.println(parseMap.get(2L));
        System.out.println("-----------------");

    }

    //跨应用-条件查询
    @Test
    public void test5(){
        //此处可尝试用builder模式


        String jsonPerson = JSON.toJSONString(user);
        JSONObject jsonObjPerson = JSON.parseObject(jsonPerson);
        String name = jsonObjPerson.getString("name");
        assertEquals("王力宏", name);

        Integer age = jsonObjPerson.getInteger("age");
        assertEquals(Long.valueOf("18"), Long.valueOf(String.valueOf(age)));
        System.out.println("------");
    }
}
