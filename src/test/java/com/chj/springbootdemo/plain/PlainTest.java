package com.chj.springbootdemo.plain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chj.springbootdemo.domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PlainTest {

    private Person person;

    @Before
    public void setUp() throws Exception {
        person = new Person();
        person.setName("王力宏");
        person.setAge(18);
    }

    @Test
    public void testFastJson1() {
        Date date = new Date();
        String strDate = JSON.toJSONString(date);
        System.out.println("strDate=" + strDate);
    }
    @Test
    public void test2() {
        Person person = new Person();
        person.setName("leehome");
        person.setAge(18);
        String jsonPerson = JSON.toJSONString(person);
        Person parsePerson = JSON.parseObject(jsonPerson, Person.class);
        System.out.println("------");
    }
    @Test
    public void test3(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("zhoujielun"));
        list.add(new Person("linjunjie"));
        String jsonList = JSON.toJSONString(list);
        List<Person> parseList = JSON.parseArray(jsonList, Person.class);
        System.out.println("-----------------");
    }
    @Test
    public void test4(){
        Map<Long,Person> map = new HashMap<>();
        map.put(1L,new Person("林志玲"));
        map.put(2L,new Person("林俊杰"));
        String jsonMap = JSON.toJSONString(map) ;

        Map<Long, Person> parseMap = JSON.parseObject(jsonMap,
                new TypeReference<Map<Long, Person>>() {});
        System.out.println(parseMap.get(2L));
        System.out.println("-----------------");

    }

    //跨应用-条件查询
    @Test
    public void test5(){
        //此处可尝试用builder模式


        String jsonPerson = JSON.toJSONString(person);
        JSONObject jsonObjPerson = JSON.parseObject(jsonPerson);
        String name = jsonObjPerson.getString("name");
        assertEquals("王力宏", name);

        Integer age = jsonObjPerson.getInteger("age");
        assertEquals(Long.valueOf("18"), Long.valueOf(String.valueOf(age)));
        System.out.println("------");
    }
}
