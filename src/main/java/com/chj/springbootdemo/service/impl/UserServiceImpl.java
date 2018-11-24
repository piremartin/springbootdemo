package com.chj.springbootdemo.service.impl;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.repository.UserRepository;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveAll(List<User> list) {
        userRepository.saveAll(list);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void testTxPrivateLocal(User user) {
        txPrivateLocal(user);
    }

    private void txPrivateLocal(User user) {
        userRepository.save(user);
    }

    //invoke本类中的
    @Override
    public void testTxPublicInterface(User user) {
//        save(user);
    }
}
