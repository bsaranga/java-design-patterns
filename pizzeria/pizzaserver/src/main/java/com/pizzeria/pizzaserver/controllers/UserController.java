package com.pizzeria.pizzaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.pizzeria.pizzaserver.models.User;
import com.pizzeria.pizzaserver.services.UserManagerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    
    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User entity) {        
        return "Done";
    }
    
}
