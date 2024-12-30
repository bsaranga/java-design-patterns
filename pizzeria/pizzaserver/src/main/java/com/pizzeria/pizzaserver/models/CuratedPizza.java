package com.pizzeria.pizzaserver.models;

public record CuratedPizza(
        int id,
        String name,
        String ingredients,
        String description,
        double price) {}