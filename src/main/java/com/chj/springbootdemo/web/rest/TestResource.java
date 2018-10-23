package com.chj.springbootdemo.web.rest;

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
}
