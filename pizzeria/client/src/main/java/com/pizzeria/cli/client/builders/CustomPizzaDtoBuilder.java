package com.pizzeria.cli.client.builders;

import com.pizzeria.cli.client.dtos.CustomPizzaDto;

public class CustomPizzaDtoBuilder {
    
    private String name;
    private String crust;
    private String sauce;
    private String cheese;
    private String topping;
    private String seasoning;
    private double price;

    public CustomPizzaDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomPizzaDtoBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public CustomPizzaDtoBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public CustomPizzaDtoBuilder setCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    public CustomPizzaDtoBuilder setTopping(String topping) {
        this.topping = topping;
        return this;
    }

    public CustomPizzaDtoBuilder setSeasoning(String seasoning) {
        this.seasoning = seasoning;
        return this;
    }

    public CustomPizzaDtoBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public CustomPizzaDto build() {
        return new CustomPizzaDto(name, crust, sauce, cheese, topping, seasoning, price);
    }
}
