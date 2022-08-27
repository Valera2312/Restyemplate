package com.example.resttemplate.Controllers;

import com.example.resttemplate.Models.User;
import com.example.resttemplate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ConsumeWebService {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public String getUsers() {
        return userService.getUsers();
    }

}