package com.chj.springbootdemo.service;

import com.chj.springbootdemo.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findById(Long id);

    List<Person> findAll();

    void save(Person person);

    void deleteById(Long id);

    void testTxPrivateLocal(Person person);

    void testTxPublicInterface(Person person);

}
