package com.aselsis.springbootworkitemdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/user")
    String hello() {
        return "Hello World, Spring Boot!";
    }

}