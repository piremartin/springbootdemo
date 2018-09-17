package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id){
        Person person = personService.findById(id).orElse(Person.PersonBuilder.aPerson().build());
        return ResponseEntity.ok(person);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Person person){
        personService.save(person);
        return ResponseEntity.ok().build();
    }
}
