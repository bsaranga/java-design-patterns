package com.pizzeria.pizzaserver.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.pizzeria.pizzaserver.dto.OrderDto;
import com.pizzeria.pizzaserver.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto, @RequestHeader("Authorization") String sessionToken) {
        
        orderService.createOrder(orderDto, sessionToken);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("setting order status to 'preparing'");
                orderService.updateOrderState(3, sessionToken);

                Thread.sleep(5000);
                System.out.println("setting order status to 'baking'");
                orderService.updateOrderState(4, sessionToken);

                Thread.sleep(5000);
                System.out.println("setting order status to 'wrapping'");
                orderService.updateOrderState(5, sessionToken);

                Thread.sleep(5000);
                System.out.println("setting order status to 'ready'");
                orderService.updateOrderState(6, sessionToken);

                Thread.sleep(5000);
                System.out.println("setting order status to 'delivering'");
                orderService.updateOrderState(7, sessionToken);

                Thread.sleep(5000);
                System.out.println("setting order status to 'delivered'");
                orderService.updateOrderState(8, sessionToken);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        return ResponseEntity.ok("Order created successfully");
    }

    @GetMapping("/state")
    public ResponseEntity<String> getOrderState(@RequestHeader("Authorization") String sessionToken) {
        String orderState = orderService.getOrderState(sessionToken);
        if (orderState != null) {
            return ResponseEntity.ok(orderState);
        } else {
            return ResponseEntity.status(404).body("Order state not found");
        }
    }
}
