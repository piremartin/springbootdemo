package com.chj.springbootdemo.web.rest;

import com.alibaba.fastjson.JSON;
import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import com.chj.springbootdemo.web.rest.vm.UserVM;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserVO> findById(@PathVariable Long id){
        String key = String.valueOf(id);

        User user;

        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(value)){
            user = JSON.parseObject(value, User.class);

        }else {
            user = userService.findById(id).orElse(new User());
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(user));
        }

//        log.info("记录登录日志");
//
//        LoginRecordE entity = new LoginRecordE();
//        entity.setLoginTime(Instant.now());
//        LoginRecordE save = loginRecordRepository.save(entity);
//        LoginRecordE loginRecordE = loginRecordRepository.findById(id).orElse(new LoginRecordE());

        UserDTO personDTO = userMapper.toDTO(user);
        UserVO vo = userMapper.toVO(personDTO);

        return ResponseEntity.ok(vo);
    }

    @PostMapping("/save")
    public ResponseEntity<UserVO> save(@RequestBody UserVM vm){
        UserDTO userDTO = userMapper.vmToDto(vm);
        UserDTO saved = userService.save(userDTO);
        UserVO userVO = userMapper.toVO(saved);
        return ResponseEntity.ok(userVO);
    }

    @PostMapping("/queryCondition")
    public ResponseEntity<Page<UserVO>> queryCondition(@RequestBody UserVM vm){
        Pageable pageable = PageRequest.of(vm.getPage(), vm.getSize(), Sort.Direction.DESC, "createTime");
        UserDTO userDTO = userMapper.vmToDto(vm);

        //use default
        userDTO.setStartTime(userDTO.getStartTime()+"T00:00:00");
        userDTO.setEndTime(userDTO.getEndTime()+"T23:59:59");

        Page<UserDTO> dtoPage = userService.findByCondition(userDTO, pageable);
        List<UserVO> voList = userMapper.toVO(dtoPage.getContent());
        Page<UserVO> voPage = new PageImpl<>(voList, dtoPage.getPageable(), dtoPage.getTotalElements());
        return ResponseEntity.ok(voPage);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<UserVO>> findAll(){
        List<UserVO> list = userService.findAll()
                .stream()
                .map(userMapper::toDTO)
                .map(userMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testTxPrivateLocal")
    public ResponseEntity<Void> testTxPrivateLocal(@RequestBody User user){
        userService.testTxPrivateLocal(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testTxPublicInterface")
    public ResponseEntity<Void> testTxPublicInterface(@RequestBody User user){
        userService.testTxPublicInterface(user);
        return ResponseEntity.ok().build();
    }
}
