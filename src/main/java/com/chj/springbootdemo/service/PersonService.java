package com.chj.springbootdemo.service;

import com.chj.springbootdemo.domain.Person;

import java.util.Optional;

public interface PersonService {

    public Optional<Person> findById(Long id);

    public void save(Person person);
}
