package com.callaars.peter.userservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/welcome")
public class HelloWorldController {



    @GetMapping
    public String hello() {
        return "Hello world";
    }

}
