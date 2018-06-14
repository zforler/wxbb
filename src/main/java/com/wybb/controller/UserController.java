package com.wybb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wybb.entity.user.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/")
    public String index() {
        return "Hello page";
    }
	
	
    @GetMapping("/user")
    public User getJson() {
        User user = new User();
        user.setUserId("123");
        user.setUserName("Tom");
        return user;
    }
    
 
}
