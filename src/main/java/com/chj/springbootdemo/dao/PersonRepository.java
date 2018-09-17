package com.chj.springbootdemo.dao;

import com.chj.springbootdemo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
