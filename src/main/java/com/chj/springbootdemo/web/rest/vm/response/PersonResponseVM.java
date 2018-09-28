package com.chj.springbootdemo.web.rest.vm.response;

import com.chj.springbootdemo.domain.Person;
import lombok.Data;

@Data
public class PersonResponseVM {

    private String name;
    private Integer age;
    private String imId;
    private String imToken;

    public static PersonResponseVM fromPerson(Person person){
        if(person == null){
            return new PersonResponseVM();
        }
        PersonResponseVM personResponseVM= new PersonResponseVM();
        personResponseVM.setName(person.getName());
        personResponseVM.setAge(person.getAge());
        personResponseVM.setImId(person.getImId());
        personResponseVM.setImToken(person.getImToken());
        return personResponseVM;
    }
}
