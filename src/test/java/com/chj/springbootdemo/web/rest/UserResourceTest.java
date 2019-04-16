package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Test
    public void test_batch_insert(){
        List<User> users = new ArrayList<>();

        Long id = 10031L;
        int age = 22;
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(id);
            user.setName("jay");
            user.setAge(age);
            user.setCreateTime(now);
            users.add(user);

            id++;
            age++;
        }

        String sql = "insert into user(id, `name`, age, create_time) value (:id, :name, :age, :createTime)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(users.toArray());
        long start = System.currentTimeMillis();
        System.out.println("start:"+start);
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
        long end = System.currentTimeMillis();
        System.out.println("end:"+end);
        System.out.println("cost millis:"+(end - start));

    }
}