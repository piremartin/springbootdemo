package com.chj.springbootdemo.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestResource {


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
