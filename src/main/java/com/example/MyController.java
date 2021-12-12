package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyClass myClass;

    @RequestMapping("/hello")
    public String getResult() {
        return myClass.helloWorld();
    }

    @RequestMapping("/getUser")
    public User getUser() {
        return myClass.getUser();
    }
}
