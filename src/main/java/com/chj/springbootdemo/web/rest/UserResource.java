package com.chj.springbootdemo.web.rest;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.repository.UserRepository;
import com.chj.springbootdemo.service.UserService;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.service.mapper.UserMapper;
import com.chj.springbootdemo.web.rest.vm.UserVM;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/add-by-named-jdbc")
    public ResponseEntity addByNamedJdbc(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/named-jdbc/query1")
    public ResponseEntity query1() {
        String sql = "select * from user where name like :name";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "n%");
        List<User> users = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(User.class));
        return ResponseEntity.ok(users);
    }


    @GetMapping("/find-by-example")
    public ResponseEntity<List<UserDTO>> findByExample(){
        User user = new User();
        user.setName("王力宏");
        Example<User> example = Example.of(user);
        List<UserDTO> list = userService.findAll(example);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find-by-example-matcher")
    public ResponseEntity<List<UserDTO>> findByExampleMatcher(){
        User user = new User();
        user.setName("caiyi");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<User> example = Example.of(user, matcher);
        List<UserDTO> list = userService.findAll(example);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<UserVO> findById(@RequestParam Long id) {
        UserDTO dto = userService.findById(id);
        UserVO vo = userMapper.toVO(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserVO> findOne(@NotNull(message = "入参不能为空") @PathVariable Long id) {
        UserDTO dto = userService.findById(id);
        UserVO vo = userMapper.toVO(dto);
        return ResponseEntity.ok(vo);
    }

    private void check(Integer age){
        if (age!=null && age < 1){
            throw new RuntimeException("illegal param");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<UserVO> save(@Valid @RequestBody UserVM vm) {
        UserDTO userDTO = userMapper.vmToDto(vm);
        UserDTO saved = userService.save(userDTO);
        UserVO userVO = userMapper.toVO(saved);
        return ResponseEntity.ok(userVO);
    }

    @PostMapping("/queryCondition")
    public ResponseEntity<Page<UserVO>> queryCondition(@RequestBody UserVM vm) {
        Pageable pageable = PageRequest.of(vm.getPage(), vm.getSize(), Sort.Direction.DESC, "createTime");
        UserDTO userDTO = userMapper.vmToDto(vm);

        //use default
//        userDTO.setStartTime(userDTO.getStartTime() + "T00:00:00");
//        userDTO.setEndTime(userDTO.getEndTime() + "T23:59:59");

        Page<UserDTO> dtoPage = userService.findByCondition(userDTO, pageable);
        List<UserVO> voList = userMapper.toVO(dtoPage.getContent());
        Page<UserVO> voPage = new PageImpl<>(voList, dtoPage.getPageable(), dtoPage.getTotalElements());
        return ResponseEntity.ok(voPage);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<UserVO>> findAll() {
        List<UserVO> list = userService.findAll()
                .stream()
                .map(userMapper::toDTO)
                .map(userMapper::toVO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testTxPrivateLocal")
    public ResponseEntity<Void> testTxPrivateLocal(@RequestBody User user) {
        userService.testTxPrivateLocal(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testTxPublicInterface")
    public ResponseEntity<Void> testTxPublicInterface(@RequestBody User user) {
        userService.testTxPublicInterface(user);
        return ResponseEntity.ok().build();
    }
}
