package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.service.PersonService;
import com.chj.springbootdemo.web.rest.vm.response.PersonResponseVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping("/testTxPrivateLocal")
    public ResponseEntity<Void> testTxPrivateLocal(@RequestBody Person person){
        personService.testTxPrivateLocal(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testTxPublicInterface")
    public ResponseEntity<Void> testTxPublicInterface(@RequestBody Person person){
        personService.testTxPublicInterface(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/batchGenYunxin")
    public ResponseEntity<Void> batchGenYunxin(){
        List<Long> ids = personService.findAll().stream().map(Person::getId).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        for(Long id : ids){
            String imId = "100";
            String imToken = "200";
            personService.updateImIdAndImTokenById(id, imId, imToken);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PersonResponseVM> findById(@PathVariable("id") Long id){
        PersonResponseVM personResponseVM;
        Person person;
        Optional<Person> optionalPerson = personService.findById(id);
        if(optionalPerson.isPresent()){
            person = optionalPerson.get();
        }else {
            person = Person.PersonBuilder.aPerson().build();
        }
        personResponseVM = PersonResponseVM.fromPerson(person);
        return ResponseEntity.ok(personResponseVM);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<PersonResponseVM>> findAll(){
        List<PersonResponseVM> list = personService.findAll()
                .stream()
                .map(PersonResponseVM::fromPerson)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Person person){
        personService.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
