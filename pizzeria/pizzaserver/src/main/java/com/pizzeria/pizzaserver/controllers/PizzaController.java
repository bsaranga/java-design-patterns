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
                .map(component -> new PizzaIngredientDto(component.id(), component.name(), component.type(), component.description(), component.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/curated")
    public List<CuratedPizzaDto> curatedPizzas() {
        List<CuratedPizza> curatedPizzas = pizzaService.getAllCuratedPizzas();
        return curatedPizzas.stream()
                .map(pizza -> new CuratedPizzaDto(pizza.id(), pizza.name(), pizza.ingredients(), pizza.description(), pizza.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/crust")
    public List<PizzaIngredientDto> randomPizzaCrusts() {
        List<PizzaIngredient> crusts = pizzaService.getRandomPizzaIngredientsByType("crust");
        return crusts.stream()
                .map(crust -> new PizzaIngredientDto(crust.id(), crust.name(), crust.type(), crust.description(), crust.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/sauce")
    public List<PizzaIngredientDto> randomPizzaSauces() {
        List<PizzaIngredient> sauces = pizzaService.getRandomPizzaIngredientsByType("sauce");
        return sauces.stream()
                .map(sauce -> new PizzaIngredientDto(sauce.id(), sauce.name(), sauce.type(), sauce.description(), sauce.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/cheese")
    public List<PizzaIngredientDto> randomPizzaCheeses() {
        List<PizzaIngredient> cheeses = pizzaService.getRandomPizzaIngredientsByType("cheese");
        return cheeses.stream()
                .map(cheese -> new PizzaIngredientDto(cheese.id(), cheese.name(), cheese.type(), cheese.description(), cheese.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/toppings")
    public List<PizzaIngredientDto> randomPizzaToppings() {
        List<PizzaIngredient> toppings = pizzaService.getRandomPizzaIngredientsByType("topping");
        return toppings.stream()
                .map(topping -> new PizzaIngredientDto(topping.id(), topping.name(), topping.type(), topping.description(), topping.price()))
                .collect(Collectors.toList());
    }

    @GetMapping("/pizza/seasoning")
    public List<PizzaIngredientDto> randomPizzaSeasonings() {
        List<PizzaIngredient> seasonings = pizzaService.getRandomPizzaIngredientsByType("seasoning");
        return seasonings.stream()
                .map(seasoning -> new PizzaIngredientDto(seasoning.id(), seasoning.name(), seasoning.type(), seasoning.description(), seasoning.price()))
                .collect(Collectors.toList());
    }
}
