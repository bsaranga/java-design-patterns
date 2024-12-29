package com.pizzeria.pizzaserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User (int id, String username, String email, String address, String phone) {}