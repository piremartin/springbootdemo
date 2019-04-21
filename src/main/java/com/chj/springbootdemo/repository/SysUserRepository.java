package com.chj.springbootdemo.repository;

import com.chj.springbootdemo.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chehaojie
 * @date 2019/04/21 23:16
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
