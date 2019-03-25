package com.chj.springbootdemo.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chj.springbootdemo.exception.BadRequestException;
import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestResource {

    @GetMapping("/handle-exception/{tag}")
    public ResponseEntity<Map<String,Object>> newException(@Range(min = 1, max = 2, message = "not in range") @PathVariable Integer tag) throws Exception{
        Map<String, Object> map = new HashMap<>();
        if (tag==1) {
            throw new BadRequestException();
        }else if (tag==2){
            throw new RuntimeException("this is 500 error");
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/a")
    public ResponseEntity<Map<String, Object>> testA(){
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("msg", "throw an exception");
//        throw new RuntimeException(map.toString());
        return ResponseEntity.ok(map);
    }


    @PostMapping("/testPathVariable/{id}")
    public ResponseEntity<Long> testPathVariable(@PathVariable Long id){

        return ResponseEntity.ok(id);
    }

    @PostMapping("/testRequestParam")
    public ResponseEntity<Long> testRequestParam(@RequestParam Long id){

        return ResponseEntity.ok(id);
    }

    @PostMapping("/testFastJson")
    public ResponseEntity<UserVO> testFastJson(){

        User user = new User();
        user.setName("leehome");
        user.setAge(18);

        String jsonPerson = JSON.toJSONString(user);
        System.out.println(jsonPerson);

        String singleQuote = JSON.toJSONString(user, SerializerFeature.UseSingleQuotes);
        System.out.println(singleQuote);

        User userFromJson = JSON.parseObject(jsonPerson, User.class);
        System.out.println(userFromJson);





        return ResponseEntity.ok(new UserVO());
    }

}
