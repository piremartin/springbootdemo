package com.chj.springbootdemo.web.rest.vo;

import com.chj.springbootdemo.domain.Person;
import lombok.Data;

@Data
public class PersonVO {

    private String name;
    private Integer age;


    public static PersonVO fromEntity(Person person){
        if (person==null){
            return new PersonVO();
        }
        PersonVO personVO= new PersonVO();
        personVO.setName(person.getName());
        personVO.setAge(person.getAge());
        return personVO;

    }
}
