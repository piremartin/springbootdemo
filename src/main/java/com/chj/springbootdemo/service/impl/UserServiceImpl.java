package com.chj.springbootdemo.service.impl;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.repository.UserRepository;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Page<UserDTO> findByCondition(UserDTO userDTO, Pageable pageable) {
        Long id = userDTO.getId();
        String name = userDTO.getName();
        Integer age = userDTO.getAge();
        String startTime = userDTO.getStartTime();
        String endTime = userDTO.getEndTime();

        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)-> {
            List<Predicate> predicates = new ArrayList<>();

            if (id!=null){
                predicates.add(cb.equal(root.get("id"), id));
            }
            if (StringUtils.isNotBlank(name)){
                predicates.add(cb.like(root.get("name"), name+"%"));
            }
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
                predicates.add(cb.between(root.get("createTime"), LocalDateTime.parse(startTime), LocalDateTime.parse(endTime)));
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
