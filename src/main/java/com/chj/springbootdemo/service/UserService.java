package com.chj.springbootdemo.service;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * dev2
 * @author chehaojie
 */
public interface UserService {

    List<UserDTO> findAll(Example<User> example);

    Page<UserDTO> findByCondition(UserDTO userDTO, Pageable pageable);

    UserDTO findById(Long id);

    List<User> findAll();

    UserDTO save(UserDTO userDTO);

    void saveAll(List<User> list);

    void deleteById(Long id);

    void testTxPrivateLocal(User user);

    void testTxPublicInterface(User user);

}
