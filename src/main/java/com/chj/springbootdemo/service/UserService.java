package com.chj.springbootdemo.service;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAll();

    UserDTO save(UserDTO userDTO);

    void saveAll(List<User> list);

    void deleteById(Long id);

    void testTxPrivateLocal(User user);

    void testTxPublicInterface(User user);

}
