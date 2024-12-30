package com.pizzeria.cli.client.state.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.dtos.CuratedPizzaDto;

@Component
public class ResourceState {
    public List<CuratedPizzaDto> curatedPizzas = new ArrayList<>();

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
}
