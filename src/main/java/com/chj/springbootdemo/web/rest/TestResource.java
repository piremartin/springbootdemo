package com.chj.springbootdemo.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.web.rest.vo.PersonVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestResource {

    @PostMapping("/testPathVariable/{id}")
    public ResponseEntity<Long> testPathVariable(@PathVariable Long id){

        return ResponseEntity.ok(id);
    }

    @PostMapping("/testRequestParam")
    public ResponseEntity<Long> testRequestParam(@RequestParam Long id){

        return ResponseEntity.ok(id);
    }

    @PostMapping("/testFastJson")
    public ResponseEntity<PersonVO> testFastJson(){

        Person person = new Person();
        person.setName("leehome");
        person.setAge(18);

        String jsonPerson = JSON.toJSONString(person);
        System.out.println(jsonPerson);

        String singleQuote = JSON.toJSONString(person, SerializerFeature.UseSingleQuotes);
        System.out.println(singleQuote);

        Person personFromJson = JSON.parseObject(jsonPerson, Person.class);
        System.out.println(personFromJson);





        PersonVO personVO = PersonVO.fromEntity(person);
        return ResponseEntity.ok(personVO);
    }

}
