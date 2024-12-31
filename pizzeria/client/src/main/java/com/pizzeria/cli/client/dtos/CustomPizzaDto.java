package com.pizzeria.cli.client.dtos;

public record CustomPizzaDto(String name, String crust, String sauce, String cheese, String topping, String seasoning, double price) {
    
}
