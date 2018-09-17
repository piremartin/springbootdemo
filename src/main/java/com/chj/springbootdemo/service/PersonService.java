package com.chj.springbootdemo.service;

import com.chj.springbootdemo.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public Optional<Person> findById(Long id);

    public List<Person> findAll();

    public void save(Person person);

    public void deleteById(Long id);

}
