package com.pizzeria.pizzaserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.pizzaserver.models.PizzaIngredient;
import com.pizzeria.pizzaserver.services.PizzaService;
import java.util.stream.Collectors;
import com.pizzeria.pizzaserver.dto.CuratedPizzaDto;
import com.pizzeria.pizzaserver.dto.PizzaIngredientDto;
import com.pizzeria.pizzaserver.models.CuratedPizza;

@RestController()
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/pizza/ingredients")
    public List<PizzaIngredientDto> pizzaComponents() {
        List<PizzaIngredient> components = pizzaService.getAllPizzaIngredients();
        return components.stream()
                .map(component -> new PizzaIngredientDto(component.name(), component.type(), component.description(), component.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/curated")
    public List<CuratedPizzaDto> curatedPizzas() {
        List<CuratedPizza> curatedPizzas = pizzaService.getAllCuratedPizzas();
        return curatedPizzas.stream()
                .map(pizza -> new CuratedPizzaDto(pizza.id(), pizza.name(), pizza.ingredients(), pizza.description(), pizza.price()))
                .collect(Collectors.toList());
    }
}
