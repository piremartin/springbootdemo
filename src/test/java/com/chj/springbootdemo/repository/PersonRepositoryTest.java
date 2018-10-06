package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.Person;
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
public class PersonRepositoryTest {

//    private Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testLike(){
        List<Person> personList = personRepository.findByNameLike("j%");
//        personList.forEach(p-> logger.debug("name --- {}", p.getName()));
        personList.forEach(p-> System.out.println("name --- "+ p.getName()));
    }

    @Test
    public void testTop(){
        List<Person> list = personRepository.findTop3ByName("林志炫6");
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
    @Test
    public void testSort(){
        List<Person> list = personRepository.findByName("林志炫6",new Sort(Sort.Direction.DESC,"id"));
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
    @Test
    public void testPage(){
        Page<Person> list = personRepository.findByName("林志炫6",PageRequest.of(0,3));
        list.forEach(p-> System.out.println("id="+p.getId()+",name="+p.getName()));
    }
}