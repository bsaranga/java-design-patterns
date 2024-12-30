package com.pizzeria.pizzaserver.dto;

public record CuratedPizzaDto(
    String name,
    String ingredients,
    String description,
    double price
) {}
