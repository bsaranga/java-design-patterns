package com.pizzeria.pizzaserver.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import com.pizzeria.pizzaserver.dto.OrderDto;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto, @RequestHeader("Authorization") String sessionToken) {
        // Process the orderDto and sessionToken
        return ResponseEntity.ok("Order created successfully");
    }
}
