package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import com.chj.springbootdemo.web.rest.vm.UserVM;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

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

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserVO> getById(@PathVariable Long id){
        User user = new User();
        user.setName("leehome");
        user.setAge(18);

        return ResponseEntity.ok(new UserVO());

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


    @GetMapping("/find/{id}")
    public ResponseEntity<UserVO> findById(@PathVariable Long id){
        User user = userService.findById(id).orElse(new User());

        UserDTO personDTO = userMapper.toDTO(user);
        UserVO userVO = userMapper.toVO(personDTO);
        return ResponseEntity.ok(userVO);
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
}
