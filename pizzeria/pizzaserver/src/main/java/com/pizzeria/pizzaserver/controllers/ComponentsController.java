package com.pizzeria.pizzaserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.pizzaserver.models.PizzaComponent;
import com.pizzeria.pizzaserver.services.ComponentsService;

@RestController
public class ComponentsController {

    @Autowired
    private ComponentsService componentsService;

    @GetMapping("/greeting")
    public List<PizzaComponent> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return componentsService.getAllPizzaComponents();
    }
}
