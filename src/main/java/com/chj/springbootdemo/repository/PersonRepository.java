package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameLike(String name);

    List<Person> findTop3ByName(String name);

    List<Person> findByName(String name, Sort sort);
    Page<Person> findByName(String name, Pageable pageable);
}
