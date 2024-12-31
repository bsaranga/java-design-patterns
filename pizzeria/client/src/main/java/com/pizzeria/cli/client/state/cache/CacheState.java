package com.pizzeria.cli.client.state.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.dtos.CuratedPizzaDto;
import com.pizzeria.cli.client.dtos.PizzaIngredientDto;

@Component
public class CacheState {
    
    public List<CuratedPizzaDto> curatedPizzas = new ArrayList<>();

    public List<PizzaIngredientDto> crusts = new ArrayList<>();
    public List<PizzaIngredientDto> sauces = new ArrayList<>();
    public List<PizzaIngredientDto> cheeses = new ArrayList<>();
    public List<PizzaIngredientDto> toppings = new ArrayList<>();
    public List<PizzaIngredientDto> seasonings = new ArrayList<>();

    public void addCuratedPizza(CuratedPizzaDto pizza) {
        curatedPizzas.add(pizza);
    }

    public List<CuratedPizzaDto> getCuratedPizzas() {
        return new ArrayList<>(curatedPizzas);
    }

    public CuratedPizzaDto getCuratedPizza(int id) {
        return curatedPizzas.stream().filter(pizza -> pizza.id() == id).findFirst().orElse(null);
    }

    public void addCuratedPizzas(List<CuratedPizzaDto> pizzas) {
        curatedPizzas.addAll(pizzas);
    }

    public void addCrust(List<PizzaIngredientDto> crusts) {
        crusts.addAll(crusts);
    }

    public List<PizzaIngredientDto> getCrusts() {
        return crusts;
    }

    public PizzaIngredientDto getCrust(int id) {
        return crusts.stream().filter(crust -> crust.id() == id).findFirst().orElse(null);
    }

    public void clearCrusts() {
        crusts.clear();
    }

    public void addSauce(List<PizzaIngredientDto> sauces) {
        sauces.addAll(sauces);
    }

    public List<PizzaIngredientDto> getSauces() {
        return sauces;
    }

    public PizzaIngredientDto getSauce(int id) {
        return sauces.stream().filter(sauce -> sauce.id() == id).findFirst().orElse(null);
    }

    public void clearSauces() {
        sauces.clear();
    }

    public void addCheese(List<PizzaIngredientDto> cheeses) {
        cheeses.addAll(cheeses);
    }

    public List<PizzaIngredientDto> getCheeses() {
        return cheeses;
    }

    public PizzaIngredientDto getCheese(int id) {
        return cheeses.stream().filter(cheese -> cheese.id() == id).findFirst().orElse(null);
    }

    public void clearCheeses() {
        cheeses.clear();
    }

    public void addTopping(List<PizzaIngredientDto> toppings) {
        toppings.addAll(toppings);
    }

    public List<PizzaIngredientDto> getToppings() {
        return toppings;
    }

    public PizzaIngredientDto getTopping(int id) {
        return toppings.stream().filter(topping -> topping.id() == id).findFirst().orElse(null);
    }

    public void clearToppings() {
        toppings.clear();
    }

    public void addSeasoning(List<PizzaIngredientDto> seasonings) {
        seasonings.addAll(seasonings);
    }

    public List<PizzaIngredientDto> getSeasonings() {
        return seasonings;
    }

    public PizzaIngredientDto getSeasoning(int id) {
        return seasonings.stream().filter(seasoning -> seasoning.id() == id).findFirst().orElse(null);
    }

    public void clearSeasonings() {
        seasonings.clear();
    }
}
