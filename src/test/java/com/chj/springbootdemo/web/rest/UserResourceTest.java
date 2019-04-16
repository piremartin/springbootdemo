package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author chehaojie
 * @date 2019/04/15 17:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void test_query1(){
        String sql = "select * from user where name like :name";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "n%");
        List<User> users = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }

    @Test
    public void test_query2(){
        String sql = "select * from user where id<0";
        List<UserDTO> users = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDTO.class));
        System.out.println(users);
    }

    @Test
    public void test_query3(){
        String sql = "select * from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList(sql, map);
        System.out.println(maps);
    }

    @Test
    public void test_query4(){
        String sql = "select `name` from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<String> names = namedParameterJdbcTemplate.queryForList(sql, map, String.class);
        System.out.println(names);
    }
    @Test
    public void test_query5(){
        String sql = "select age from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Integer> ages = namedParameterJdbcTemplate.queryForList(sql, map, Integer.class);
        System.out.println(ages);
    }
    @Test
    public void test_query6(){
        String sql = "select id from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Long> ids = namedParameterJdbcTemplate.queryForList(sql, map, Long.class);
        System.out.println(ids);
    }

    @Test
    public void test_insert(){
        Long id = 8L;
        int age = 22;
        for (int i = 0; i < 3; i++) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", id);
            paramMap.put("age", age);
            String sql = "insert into user(id, `name`, age) value (:id, 'xiaoming', :age)";
            int update = namedParameterJdbcTemplate.update(sql, paramMap);
            System.out.println(update);
            id++;
            age++;
        }
    }
}