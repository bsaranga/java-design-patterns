package com.pizzeria.pizzaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.pizzeria.pizzaserver.dto.UserDto;
import com.pizzeria.pizzaserver.models.User;
import com.pizzeria.pizzaserver.services.UserManagerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    
    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto entity) {        
        try {
            User user = userManagerService.registerUser(entity);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
