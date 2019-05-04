package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void test_batchInsertUser() {
        LocalDateTime now = LocalDateTime.now();
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("leehome");
        user.setAge(18);
        user.setCreateTime(now);
        users.add(user);

        user = new User();
        user.setName("jay");
        user.setAge(19);
        user.setCreateTime(now);
        users.add(user);

        String sql = "insert into user(`name`, age, create_time) values(:name, :age, :createTime)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(users.toArray());
        namedParameterJdbcTemplate.batchUpdate(sql, batch);

    }

    @Test
    public void test_addUser_use_map() {
        String sql = "insert into User(`name`, age, create_time) " +
                "values('lucas', 18, :createTime)";
        Map<String, Object> map = new HashMap<>(1);
        map.put("createTime", LocalDateTime.now());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Test
    public void test_queryForObject_exist() {

        String sql = "select `name` from user where id=1";
        String name = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(1), String.class);
        Assert.assertTrue(StringUtils.hasText(name));
    }

    @Test
    public void test_queryForObject_exception_except1_actual0() {

        String sql = "select `name` from user where id=0";
        //org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
        String name = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(1), String.class);
        //这一步没有执行到
        Assert.assertTrue(StringUtils.hasText(name));
    }

    @Test
    public void test_queryForObject_exception_except1_actual2() {

        String sql = "select `name` from user where age=18";
        //org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2
        String name = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(1), String.class);
        //这一步没有执行到
        Assert.assertTrue(StringUtils.hasText(name));
    }

    @Test
    public void test_queryForObject_solve_except1_actualX() {

        String sql = "select `name` from user where age=18";
        String name = namedParameterJdbcTemplate.query(sql, new HashMap<>(1), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
                return null;
            }
        });
        Assert.assertTrue(StringUtils.hasText(name));
        System.out.println("name=" + name);
    }

    @Test
    public void test_queryForObject_solve_except1_actualX_use_lamda() {

        String sql = "select `name` from user where age=18 order by create_time desc ";
        String name = namedParameterJdbcTemplate.query(sql, new HashMap<>(1), (ResultSet resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
            return null;
        });
        Assert.assertTrue(StringUtils.hasText(name));
        System.out.println("name=" + name);
    }

    @Test
    public void test_query1() {
        String sql = "select * from user where name like :name";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "n%");
        List<User> users = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }

    @Test
    public void test_query2() {
        String sql = "select * from user where id<0";
        List<UserDTO> users = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDTO.class));
        System.out.println(users);
    }

    @Test
    public void test_query3() {
        String sql = "select * from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList(sql, map);
        System.out.println(maps);
    }

    @Test
    public void test_query4() {
        String sql = "select `name` from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<String> names = namedParameterJdbcTemplate.queryForList(sql, map, String.class);
        System.out.println(names);
    }

    @Test
    public void test_query5() {
        String sql = "select age from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Integer> ages = namedParameterJdbcTemplate.queryForList(sql, map, Integer.class);
        System.out.println(ages);
    }

    @Test
    public void test_query6() {
        String sql = "select id from user where id < :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        List<Long> ids = namedParameterJdbcTemplate.queryForList(sql, map, Long.class);
        System.out.println(ids);
    }

    @Test
    public void test_insert() {
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
    public void test_batch_insert() {
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
        System.out.println("start:" + start);
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
        long end = System.currentTimeMillis();
        System.out.println("end:" + end);
        System.out.println("cost millis:" + (end - start));

    }
}