package com.example.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //prevent a nested exception
    @GetMapping("/interceptor")
    public String interceptor() {
        return "OK";
    }

}
