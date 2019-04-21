package com.chj.springbootdemo.security;

import com.chj.springbootdemo.domain.SysUser;
import com.chj.springbootdemo.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author chehaojie
 * @date 2019/04/21 23:18
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return sysUser;
    }
}
