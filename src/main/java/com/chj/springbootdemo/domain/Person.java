package com.chj.springbootdemo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    private String imId;
    private String imToken;

    private Person(){}


    public static final class PersonBuilder {
        private Long id;
        private String name;
        private Integer age;
        private String imId;
        private String imToken;

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

        public PersonBuilder withImId(String imId) {
            this.imId = imId;
            return this;
        }

        public PersonBuilder withImToken(String imToken) {
            this.imToken = imToken;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setAge(age);
            person.setImId(imId);
            person.setImToken(imToken);
            return person;
        }
    }
}
