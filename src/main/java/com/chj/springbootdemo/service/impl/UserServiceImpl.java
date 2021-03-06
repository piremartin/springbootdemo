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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //    private final StringRedisTemplate stringRedisTemplate;
//    private final String Key_User = "user:";
    private final String Key_Users = "users";


//    @Autowired
//    private RedisTemplate<String, Object> template;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<UserDTO> findAll(Example<User> example) {
        List<User> userList = userRepository.findAll(example);
        return userMapper.toDTO(userList);
    }

    @Override
    public Page<UserDTO> findByCondition(UserDTO userDTO, Pageable pageable) {
        Long id = 1L;//userDTO.getId();
        String name = userDTO.getName();

        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(cb.equal(root.get("id"), id));
            }
            if (StringUtils.isNotBlank(name)) {
                predicates.add(cb.like(root.get("name"), name + "%"));
            }

            try {
                LocalDateTime startTime = LocalDateTime.now();//LocalDateTime.parse(userDTO.getStartTime());
                LocalDateTime endTime = LocalDateTime.now();//LocalDateTime.parse(userDTO.getEndTime());
                predicates.add(cb.between(root.get("createTime"), startTime, endTime));
            } catch (Exception e) {
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


    @Override
    public UserDTO findById(Long id) {
        String key = Key_Users;

        User user = (User) redisTemplate.opsForHash().get(key, String.valueOf(id));
        if (user == null) {
            log.debug("redis中id={}的User不存在", id);
            user = userRepository.findById(id).orElse(new User());
            log.debug("保存到redis:{}", user);
            redisTemplate.opsForHash().put(key, String.valueOf(id), JSON.toJSONString(user));
        }
        return userMapper.toDTO(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User save = userRepository.save(user);

        log.debug("保存到数据库后，同步保存到redis:{}", save);

        redisTemplate.opsForHash().put(Key_Users, String.valueOf(save.getId()), JSON.toJSONString(save));

        return userMapper.toDTO(save);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

        log.debug("从redis删除id={}的User", id);
        redisTemplate.opsForHash().delete(Key_Users, id);
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
