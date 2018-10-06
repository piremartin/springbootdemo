package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Transactional
    @Query("update Person t set t.imId=?2, t.imToken= ?3 where t.id=?1")
    Integer updateImIdAndImTokenById_way1(Long id, String imId, String imToken);

    @Modifying
    @Transactional
    @Query("update Person t set t.imId=:im_Id, t.imToken=:im_Token where t.id=:id")
    Integer updateImIdAndImTokenById(@Param("id") Long id, @Param("im_Id") String imId, @Param("im_Token") String imToken);

    List<Person> findByNameLike(String name);

    List<Person> findTop3ByName(String name);

    List<Person> findByName(String name, Sort sort);
    Page<Person> findByName(String name, Pageable pageable);
}
