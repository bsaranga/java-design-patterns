package com.pizzeria.cli.client.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GreetingDTO(long id, String content) {}