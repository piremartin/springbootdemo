package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.LoginRecordE;
import org.springframework.data.jpa.repository.JpaRepository;

//todo
//为什么不需要用@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecordE, Long> {
}
