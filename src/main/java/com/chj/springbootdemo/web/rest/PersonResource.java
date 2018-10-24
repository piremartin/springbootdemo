package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.service.PersonService;
import com.chj.springbootdemo.service.dto.PersonDTO;
import com.chj.springbootdemo.service.mapper.PersonMapper;
import com.chj.springbootdemo.web.rest.vo.PersonVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonResource {

    private final PersonService personService;
    private final PersonMapper personMapper;


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PersonVO> getById(@PathVariable Long id){
        Person person = new Person();
        person.setName("leehome");
        person.setAge(18);

        PersonVO personVO = PersonVO.fromEntity(person);
        return ResponseEntity.ok(personVO);

    }

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


    @GetMapping("/find/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable Long id){
        Person person = personService.findById(id).orElse(new Person());

        PersonDTO personDTO = personMapper.toDTO(person);
        PersonVO personVO = personMapper.toVO(personDTO);
        return ResponseEntity.ok(personVO);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<PersonVO>> findAll(){
        List<PersonVO> list = personService.findAll()
                .stream()
                .map(personMapper::toDTO)
                .map(personMapper::toVO)
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
