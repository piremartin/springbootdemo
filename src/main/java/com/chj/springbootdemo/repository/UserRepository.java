package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameLike(String name);

    List<User> findTop3ByName(String name);

    List<User> findByName(String name, Sort sort);
    Page<User> findByName(String name, Pageable pageable);
}
