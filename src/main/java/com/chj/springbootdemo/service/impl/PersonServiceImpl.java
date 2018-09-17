package com.chj.springbootdemo.service.impl;

import com.chj.springbootdemo.dao.PersonRepository;
import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> findById(Long id){
        return personRepository.findById(id);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public void save(Person person){
        personRepository.save(person);
    }

    public void deleteById(Long id){
        personRepository.deleteById(id);
    }
}
