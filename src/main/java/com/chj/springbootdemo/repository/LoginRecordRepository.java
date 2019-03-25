package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;

//todo
//为什么不需要用@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
}
