package com.pizzeria.pizzaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.pizzaserver.dto.LoginDto;
import com.pizzeria.pizzaserver.dto.SessionDto;
import com.pizzeria.pizzaserver.dto.StatusDto;
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
    public ResponseEntity<StatusDto> registerUser(@RequestBody UserDto entity) {
        try {
            User user = userManagerService.registerUser(entity);
            return ResponseEntity.ok(new StatusDto("registered"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<SessionDto> loginUser(@RequestBody LoginDto entity) {
        try {
            SessionDto session = userManagerService.loginUser(entity);
            return ResponseEntity.ok(session);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/user/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody SessionDto entity) {
        try {
            userManagerService.logoutUser(entity);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
