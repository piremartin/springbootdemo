package com.chj.springbootdemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "c_person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    private String addr;

    private Person(){}


    public static final class PersonBuilder {
        private Long id;
        private String name;
        private Integer age;
        private String addr;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withAddr(String addr) {
            this.addr = addr;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setAge(age);
            person.setAddr(addr);
            return person;
        }
    }
}
