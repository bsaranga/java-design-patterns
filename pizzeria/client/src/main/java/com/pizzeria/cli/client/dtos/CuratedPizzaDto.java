package com.pizzeria.cli.client.dtos;

public record CuratedPizzaDto(
    int id,
    String name,
    String ingredients,
    String description,
    double price
) {}
