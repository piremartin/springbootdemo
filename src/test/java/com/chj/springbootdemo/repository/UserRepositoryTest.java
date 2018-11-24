package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

//    private Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLike(){
        List<User> userList = userRepository.findByNameLike("j%");
//        userList.forEach(p-> logger.debug("name --- {}", p.getName()));
        userList.forEach(p-> System.out.println("name --- "+ p.getName()));
    }

    @Test
    public void testTop(){
        List<User> list = userRepository.findTop3ByName("林志炫6");
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
    @Test
    public void testSort(){
        List<User> list = userRepository.findByName("林志炫6",new Sort(Sort.Direction.DESC,"id"));
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
    @Test
    public void testPage(){
        Page<User> list = userRepository.findByName("林志炫6",PageRequest.of(0,3));
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
}