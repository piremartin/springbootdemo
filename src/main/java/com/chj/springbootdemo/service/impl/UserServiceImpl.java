package com.chj.springbootdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.repository.UserRepository;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final String Key_User = "user:";


    @Override
    public Page<UserDTO> findByCondition(UserDTO userDTO, Pageable pageable) {
        Long id = userDTO.getId();
        String name = userDTO.getName();

        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)-> {
            List<Predicate> predicates = new ArrayList<>();

            if (id!=null){
                predicates.add(cb.equal(root.get("id"), id));
            }
            if (StringUtils.isNotBlank(name)){
                predicates.add(cb.like(root.get("name"), name+"%"));
            }

            try {
                LocalDateTime startTime = LocalDateTime.parse(userDTO.getStartTime());
                LocalDateTime endTime = LocalDateTime.parse(userDTO.getEndTime());
                predicates.add(cb.between(root.get("createTime"), startTime, endTime));
            }catch (Exception e){
            }

            Predicate[] array = new Predicate[predicates.size()];
            return query.where(predicates.toArray(array)).getRestriction();
        };
        Page<User> entityPage = userRepository.findAll(specification, pageable);
        List<UserDTO> dtoList = userMapper.toDTO(entityPage.getContent());
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    @Override
    public void saveAll(List<User> list) {
        userRepository.saveAll(list);
    }

    /**
     * fetch from redis first
     * @param id
     * @return
     */
    public UserDTO findById(Long id) {
        String key = Key_User+id;
        User user;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(value)){
            user = JSON.parseObject(value, User.class);
        }else {
            user = userRepository.findById(id).orElse(new User());
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(user));
        }
        return userMapper.toDTO(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * redis: list, map
     * @param userDTO
     * @return
     */
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
