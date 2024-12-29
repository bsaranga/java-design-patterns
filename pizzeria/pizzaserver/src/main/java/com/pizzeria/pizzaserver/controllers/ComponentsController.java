package com.pizzeria.pizzaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.pizzaserver.services.ComponentsService;

@RestController
public class ComponentsController {

    @Autowired
    private ComponentsService componentsService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        componentsService.getAllPizzaComponents();
        return "Hello World...";
    }
}
